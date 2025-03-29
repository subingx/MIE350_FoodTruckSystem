package com.example.cms;

import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.model.entity.FoodTruckOwner;
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
class FoodTruckOwnerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FoodTruckOwnerRepository ownerRepository;

    @Test
    void getFoodTruckOwner() throws Exception {
        // Create test data
        FoodTruckOwner owner = new FoodTruckOwner();
        owner.setId(101L);
        owner.setFirstName("Test");
        owner.setLastName("Owner");
        owner.setEmail("test.owner@example.com");
        owner.setPassword("password123");
        ownerRepository.save(owner);

        // Test the endpoint
        MockHttpServletResponse response = mockMvc.perform(get("/foodtruckowners/101"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(101L, receivedJson.get("id").longValue());
        assertEquals("Test", receivedJson.get("firstName").textValue());
        assertEquals("Owner", receivedJson.get("lastName").textValue());
        assertEquals("test.owner@example.com", receivedJson.get("email").textValue());
    }

    @Test
    void addFoodTruckOwner() throws Exception {
        ObjectNode ownerJson = objectMapper.createObjectNode();
        ownerJson.put("id", 901L);
        ownerJson.put("firstName", "first");
        ownerJson.put("lastName", "last");
        ownerJson.put("email", "first@last.com");
        ownerJson.put("password", "test123");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/foodtruckowners")
                                .contentType("application/json")
                                .content(ownerJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        assertTrue(ownerRepository.findById(901L).isPresent());
        FoodTruckOwner addedOwner = ownerRepository.findById(901L).get();

        assertEquals(901L, addedOwner.getId());
        assertEquals("first", addedOwner.getFirstName());
        assertEquals("last", addedOwner.getLastName());
        assertEquals("first@last.com", addedOwner.getEmail());
        assertEquals("test123", addedOwner.getPassword());
    }

    @Test
    void deleteFoodTruckOwner() throws Exception {
        // Create test data
        FoodTruckOwner owner = new FoodTruckOwner();
        owner.setId(123456L);
        owner.setFirstName("first");
        owner.setLastName("last");
        owner.setEmail("first@last.com");
        owner.setPassword("password");
        ownerRepository.save(owner);

        // Test the endpoint
        MockHttpServletResponse response = mockMvc.perform(
                        delete("/foodtruckowners/123456")
                                .contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(ownerRepository.findById(123456L).isEmpty());
    }

    @Test
    void updateFoodTruckOwner() throws Exception {
        // Create test data
        FoodTruckOwner owner = new FoodTruckOwner();
        owner.setId(12345L);
        owner.setFirstName("first");
        owner.setLastName("last");
        owner.setEmail("first@last.com");
        owner.setPassword("oldpass");
        ownerRepository.save(owner);

        ObjectNode updateJson = objectMapper.createObjectNode();
        updateJson.put("firstName", "new");
        updateJson.put("lastName", "name");
        updateJson.put("email", "new@name.com");
        updateJson.put("password", "newpass");

        MockHttpServletResponse response = mockMvc.perform(
                        put("/foodtruckowners/12345")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        FoodTruckOwner updatedOwner = ownerRepository.findById(12345L).get();
        assertEquals("new", updatedOwner.getFirstName());
        assertEquals("name", updatedOwner.getLastName());
        assertEquals("new@name.com", updatedOwner.getEmail());
        assertEquals("newpass", updatedOwner.getPassword());
    }

    @Test
    void loginFoodTruckOwner() throws Exception {
        // Create test data
        FoodTruckOwner owner = new FoodTruckOwner();
        owner.setId(606L);
        owner.setFirstName("Log");
        owner.setLastName("In");
        owner.setEmail("login@example.com");
        owner.setPassword("logpass");
        ownerRepository.save(owner);

        ObjectNode loginJson = objectMapper.createObjectNode();
        loginJson.put("email", "login@example.com");
        loginJson.put("password", "logpass");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/foodtruckowners/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode received = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("login@example.com", received.get("email").textValue());
    }
}