package com.example.cms.controller;

import com.example.cms.controller.dto.CustomerDto;
import com.example.cms.controller.exceptions.CustomerAlreadyExistsException;
import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.model.entity.Customer;
import com.example.cms.model.repository.CustomerRepository;
import com.example.cms.model.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
        if (repository.existsById(newCustomer.getId())) {
            throw new CustomerAlreadyExistsException(newCustomer.getId());
        }

        return repository.save(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    @Transactional
    void deleteCustomer(@PathVariable("id") Long customerId) {
        if (!repository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }

        favoriteRepository.deleteByCustomerId(customerId); // Spring JPA will handle this

        repository.deleteById(customerId); // Delete the customer
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
