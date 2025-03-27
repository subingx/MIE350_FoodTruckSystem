package com.example.cms;

import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.repository.CustomerRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

class CustomerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getCustomer() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(get("/customers/101"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode receivedJson = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals(101L, receivedJson.get("id").longValue());
        assertEquals("Alice", receivedJson.get("firstName").textValue());
        assertEquals("Wong", receivedJson.get("lastName").textValue());
        assertEquals("alice.wong@example.com", receivedJson.get("email").textValue());

    }

    @Test
    void addCustomer() throws Exception{

        ObjectNode customerJson = objectMapper.createObjectNode();
        customerJson.put("id", 901L);
        customerJson.put("firstName", "first");
        customerJson.put("lastName", "last");
        customerJson.put("email", "first@last.com");
        customerJson.put("password", "test123");


        MockHttpServletResponse response = mockMvc.perform(
                        post("/customers").
                                contentType("application/json").
                                content(customerJson.toString()))
                .andReturn().getResponse();

        // assert HTTP code of response is 200 OK
        assertEquals(200, response.getStatus());

        assertTrue(customerRepository.findById(901L).isPresent());
        Customer addedCustomer = customerRepository.findById(901L).get();

        // Assert the details of the students are correct
        assertEquals(901L, addedCustomer.getId());
        assertEquals("first", addedCustomer.getFirstName());
        assertEquals("last", addedCustomer.getLastName());
        assertEquals("first@last.com", addedCustomer.getEmail());
        assertEquals("test123", addedCustomer.getPassword());
    }

    @Test
    void deleteCustomer() throws Exception{
        Customer c = new Customer();
        c.setId(123456L);
        c.setFirstName("first");
        c.setLastName("last");
        c.setEmail("first@last.com");
        customerRepository.save(c);

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/customers/123456").
                                contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(customerRepository.findById(123456L).isEmpty());
    }

    @Test
    void updateCustomer() throws Exception{
        Customer c = new Customer();
        c.setId(12345L);
        c.setFirstName("first");
        c.setLastName("last");
        c.setEmail("first@last.com");
        customerRepository.save(c);

        ObjectNode updateJson = objectMapper.createObjectNode();
        updateJson.put("firstName", "new");
        updateJson.put("lastName", "name");
        updateJson.put("email", "new@name.com");

        MockHttpServletResponse response = mockMvc.perform(
                        put("/customers/12345")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        Customer updatedCustomer = customerRepository.findById(12345L).get();
        assertEquals("new", updatedCustomer.getFirstName());
        assertEquals("name", updatedCustomer.getLastName());
        assertEquals("new@name.com", updatedCustomer.getEmail());
    }

    @Test
    void loginCustomer() throws Exception {
        Customer c = new Customer();
        c.setId(606L);
        c.setFirstName("Log");
        c.setLastName("In");
        c.setEmail("login@example.com");
        c.setPassword("logpass");
        customerRepository.save(c);

        ObjectNode loginJson = objectMapper.createObjectNode();
        loginJson.put("email", "login@example.com");
        loginJson.put("password", "logpass");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/customers/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(loginJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        ObjectNode received = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        assertEquals("login@example.com", received.get("email").textValue());
    }

}
