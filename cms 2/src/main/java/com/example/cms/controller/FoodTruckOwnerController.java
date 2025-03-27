package com.example.cms.controller;

import com.example.cms.controller.dto.FoodTruckOwnerDto;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.model.entity.FoodTruckOwner;
import com.example.cms.model.repository.FoodTruckOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FoodTruckOwnerController {

    @Autowired
    private final FoodTruckOwnerRepository repository;

    public FoodTruckOwnerController(FoodTruckOwnerRepository repository) {
        this.repository = repository;
    }

    // Retrieve all food truck owners
    @GetMapping("/foodtruckowners")
    List<FoodTruckOwner> retrieveAllOwners() {
        return repository.findAll();
    }

    // Retrieve a single food truck owner by ID
    @GetMapping("/foodtruckowners/{id}")
    FoodTruckOwner retrieveOwner(@PathVariable("id") Long ownerId) {
        return repository.findById(ownerId)
                .orElseThrow(() -> new FoodTruckOwnerNotFoundException(ownerId));
    }

    // Create a new food truck owner
    @PostMapping("/foodtruckowners")
    FoodTruckOwner createOwner(@RequestBody FoodTruckOwner newOwner) {
        return repository.save(newOwner);
    }


    // Delete a food truck owner by ID
    @DeleteMapping("/foodtruckowners/{id}")
    void deleteOwner(@PathVariable("id") Long ownerId) {
        repository.deleteById(ownerId);
    }

    // Login
    @PostMapping("/foodtruckowners/login")
    FoodTruckOwner loginOwner(@RequestBody FoodTruckOwner loginInfo) {
        return repository.findByEmailAndPassword(loginInfo.getEmail(), loginInfo.getPassword());
    }

    @PutMapping("/foodtruckowners/{id}")
    FoodTruckOwner updateFoodTruckOwner(@RequestBody FoodTruckOwnerDto foodTruckOwnerDto, @PathVariable("id") Long ownerId) {

        return repository.findById(ownerId)
                .map(owner -> {
                    owner.setFirstName(foodTruckOwnerDto.getFirstName());
                    owner.setLastName(foodTruckOwnerDto.getLastName());
                    owner.setEmail(foodTruckOwnerDto.getEmail());
                    owner.setPassword(foodTruckOwnerDto.getPassword());
                    return repository.save(owner);
                })
                .orElseGet(() -> {
                    FoodTruckOwner newOwner = new FoodTruckOwner();
                    newOwner.setId(ownerId);
                    newOwner.setFirstName(foodTruckOwnerDto.getFirstName());
                    newOwner.setLastName(foodTruckOwnerDto.getLastName());
                    newOwner.setEmail(foodTruckOwnerDto.getEmail());
                    newOwner.setPassword(foodTruckOwnerDto.getPassword());
                    return repository.save(newOwner);
                });
    }

}
