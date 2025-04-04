package com.example.cms.controller;

import com.example.cms.controller.dto.FoodTruckOwnerDto;
import com.example.cms.controller.exceptions.CustomerNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckOwnerAlreadyExistsException;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.model.entity.FoodTruck;
import com.example.cms.model.entity.FoodTruckOwner;
import com.example.cms.model.repository.FavoriteRepository;
import com.example.cms.model.repository.FoodTruckOwnerRepository;
import com.example.cms.model.repository.FoodTruckRepository;
import com.example.cms.model.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FoodTruckOwnerController {

    @Autowired
    private final FoodTruckOwnerRepository repository;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

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
        if (repository.existsById(newOwner.getId())) {
            throw new FoodTruckOwnerAlreadyExistsException(newOwner.getId());
        }
        return repository.save(newOwner);
    }


    // Delete a food truck owner by ID
    @DeleteMapping("/foodtruckowners/{id}")
    @Transactional
    void deleteOwner(@PathVariable("id") Long ownerId) {
        if (!repository.existsById(ownerId)) {
            throw new FoodTruckOwnerNotFoundException(ownerId);
        }

        List<FoodTruck> trucks = foodTruckRepository.findByOwnerId(ownerId);
        for (FoodTruck truck : trucks) {
            // Delete related favorites and menu items first
            favoriteRepository.deleteByFoodTruckCode(truck.getCode());
            menuItemRepository.deleteByFoodTruckCode(truck.getCode());
            foodTruckRepository.deleteById(truck.getCode());
        }
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
