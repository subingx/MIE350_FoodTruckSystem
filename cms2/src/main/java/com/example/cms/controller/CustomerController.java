package com.example.cms.controller;

import com.example.cms.controller.dto.CustomerDto;
import com.example.cms.controller.dto.FoodTruckOwnerDto;
import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.entity.FoodTruckOwner;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private final CustomerRepository repository;

    @Autowired
    private FavoriteRepository favoriteRepository;

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

//    @DeleteMapping("/customers/{id}")
//    void deleteCustomer(@PathVariable("id") Long customerId) {
//        repository.deleteById(customerId);
//    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable("id") Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        favoriteRepository.deleteByCustomerId(customerId);

        repository.deleteById(customerId);
    }

    // Login
    @PostMapping("/customers/login")
    Customer login(@RequestBody Customer loginInfo) {
        return repository.findByEmailAndPassword(loginInfo.getEmail(), loginInfo.getPassword());
    }

    @PutMapping("/customers/{id}")
    Customer updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable("id") Long id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(customerDto.getFirstName());
                    customer.setLastName(customerDto.getLastName());
                    customer.setEmail(customerDto.getEmail());
                    customer.setPassword(customerDto.getPassword());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setId(id); //Id wont be changed
                    newCustomer.setFirstName(customerDto.getFirstName());
                    newCustomer.setLastName(customerDto.getLastName());
                    newCustomer.setEmail(customerDto.getEmail());
                    newCustomer.setPassword(customerDto.getPassword());
                    return repository.save(newCustomer);
                });
    }


}
