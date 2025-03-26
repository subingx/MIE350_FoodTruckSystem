package com.example.cms.controller;

import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.FoodTruckOwner;
import com.example.cms.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customers")
    List<Customer> retrieveAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer retrieveCustomer(@PathVariable("id") Long customerId) {
        return repository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }


    @PostMapping("/customers")
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return repository.save(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long customerId) {
        repository.deleteById(customerId);
    }

    // Login
    @PostMapping("/customers/login")
    Customer login(@RequestBody Customer loginInfo) {
        return repository.findByEmailAndPassword(loginInfo.getEmail(), loginInfo.getPassword());
    }
}
