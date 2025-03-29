package com.example.cms;

import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.model.entity.FoodTruck;
import com.example.cms.model.entity.FoodTruckOwner;
import com.example.cms.model.entity.MenuItem;
import com.example.cms.model.repository.MenuItemRepository;
import com.example.cms.model.repository.FoodTruckRepository;
import com.example.cms.model.repository.FoodTruckOwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenuItemTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Autowired
    private FoodTruckOwnerRepository ownerRepository;

    private FoodTruck createTestFoodTruck(String code) {
        // Create owner first
        FoodTruckOwner owner = new FoodTruckOwner();
        owner.setId(100L);
        owner.setFirstName("Owner");
        owner.setLastName("Test");
        owner.setEmail("owner@test.com");
        ownerRepository.save(owner);

        // Create food truck
        FoodTruck truck = new FoodTruck();
        truck.setCode(code);
        truck.setName("Test Truck " + code);
        truck.setOwner(owner);
        return foodTruckRepository.save(truck);
    }

    @Test
    void getMenuItem() throws Exception {
        // Setup
        FoodTruck truck = createTestFoodTruck("TRUCK1");
        
        MenuItem item = new MenuItem();
        item.setCode("ITEM1");
        item.setName("Test Item");
        item.setPrice(9.99);
        item.setAvailable(true);
        item.setFoodTruck(truck);
        menuItemRepository.save(item);

        // Test
        MockHttpServletResponse response = mockMvc.perform(get("/menuitems/ITEM1"))
                .andReturn().getResponse();

        // Verify
        assertEquals(200, response.getStatus());
        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("ITEM1", receivedJson.get("code").textValue());
        assertEquals("Test Item", receivedJson.get("name").textValue());
        assertEquals(9.99, receivedJson.get("price").doubleValue());
    }

    @Test
    void createMenuItem() throws Exception {
        // Setup
        FoodTruck truck = createTestFoodTruck("TRUCK2");

        // Prepare request
        ObjectNode itemJson = objectMapper.createObjectNode();
        itemJson.put("code", "NEWITEM");
        itemJson.put("name", "New Item");
        itemJson.put("price", 5.99);
        itemJson.put("isAvailable", true);
        
        // Need to manually set foodTruck since we're not using DTO in the test
        ObjectNode truckJson = objectMapper.createObjectNode();
        truckJson.put("code", truck.getCode());
        itemJson.set("foodTruck", truckJson);

        // Test
        MockHttpServletResponse response = mockMvc.perform(
                post("/menuitems")
                        .contentType("application/json")
                        .content(itemJson.toString()))
                .andReturn().getResponse();

        // Verify
        assertEquals(200, response.getStatus());
        MenuItem savedItem = menuItemRepository.findById("NEWITEM").orElse(null);
        assertNotNull(savedItem);
        assertEquals("New Item", savedItem.getName());
        assertEquals("TRUCK2", savedItem.getFoodTruck().getCode());
    }

    @Test
    void updateMenuItem() throws Exception {
        // Setup
        FoodTruck truck1 = createTestFoodTruck("TRUCK3");
        FoodTruck truck2 = createTestFoodTruck("TRUCK4");

        MenuItem item = new MenuItem();
        item.setCode("ITEM2");
        item.setName("Old Name");
        item.setPrice(10.00);
        item.setAvailable(true);
        item.setFoodTruck(truck1);
        menuItemRepository.save(item);

        // Prepare update
        ObjectNode updateJson = objectMapper.createObjectNode();
        updateJson.put("name", "Updated Name");
        updateJson.put("price", 12.50);
        updateJson.put("isAvailable", false);
        updateJson.put("truckCode", "TRUCK4");

        // Test
        MockHttpServletResponse response = mockMvc.perform(
                put("/menuitems/ITEM2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson.toString()))
                .andReturn().getResponse();

        // Verify
        assertEquals(200, response.getStatus());
        MenuItem updatedItem = menuItemRepository.findById("ITEM2").orElse(null);
        assertNotNull(updatedItem);
        assertEquals("Updated Name", updatedItem.getName());
        assertEquals(12.50, updatedItem.getPrice());
        assertEquals("TRUCK4", updatedItem.getFoodTruck().getCode());
    }

    @Test
    void deleteMenuItem() throws Exception {
        // Setup
        FoodTruck truck = createTestFoodTruck("TRUCK5");
        
        MenuItem item = new MenuItem();
        item.setCode("ITEM3");
        item.setName("To Delete");
        item.setPrice(7.99);
        item.setAvailable(true);
        item.setFoodTruck(truck);
        menuItemRepository.save(item);

        // Test
        MockHttpServletResponse response = mockMvc.perform(
                delete("/menuitems/ITEM3"))
                .andReturn().getResponse();

        // Verify
        assertEquals(200, response.getStatus());
        assertFalse(menuItemRepository.findById("ITEM3").isPresent());
    }
}