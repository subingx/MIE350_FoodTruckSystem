package com.example.cms.controller;

import com.example.cms.controller.dto.FoodTruckDto;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.*;

import com.example.cms.model.repository.FoodTruckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FoodTruckController {
    @Autowired
    private final FoodTruckRepository foodTruckRepository;

    @Autowired
    private FoodTruckOwnerRepository ownerRepository;
    @Autowired
    private FoodTruckOwnerRepository foodTruckOwnerRepository;

    public FoodTruckController(FoodTruckRepository repository) {
        this.foodTruckRepository = repository;
    }

    @GetMapping("/foodtrucks")
    List<FoodTruck> retrieveAllFoodTrucks() {return foodTruckRepository.findAll();}


    @GetMapping("/foodtrucks/{code}")
    FoodTruck retrieveFoodTruck(@PathVariable("code") String truckCode) {
        return foodTruckRepository.findById(truckCode)
                .orElseThrow(() -> new FoodTruckNotFoundException(truckCode));
    }

    @PostMapping("/foodtrucks")
    FoodTruck createFoodTruck(@RequestBody FoodTruckDto foodTruckDto) {
        FoodTruck newFoodTruck = new FoodTruck();
        newFoodTruck.setName(foodTruckDto.getName());
        newFoodTruck.setCode(foodTruckDto.getCode());
        FoodTruckOwner owner = ownerRepository.findById(foodTruckDto.getOwnerId()).orElseThrow(
                () -> new FoodTruckOwnerNotFoundException(foodTruckDto.getOwnerId()) );
        newFoodTruck.setOwner(owner);
        newFoodTruck.setLocation(foodTruckDto.getLocation());
        newFoodTruck.setOperatingHours(foodTruckDto.getOperatingHours());
        return foodTruckRepository.save(newFoodTruck);
    }

    @PutMapping("/foodtrucks/{code}")
    FoodTruck updateFoodTruck(@RequestBody FoodTruckDto foodTruckDto, @PathVariable("code") String truckCode) {
        return foodTruckRepository.findById(truckCode)
                .map(foodTruck -> {
                    foodTruck.setName(foodTruckDto.getName());
                    FoodTruckOwner owner = foodTruckOwnerRepository.findById(foodTruckDto.getOwnerId()).orElseThrow(
                            () -> new FoodTruckOwnerNotFoundException(foodTruckDto.getOwnerId()));
                    foodTruck.setOwner(owner);
                    foodTruck.setOperatingHours(foodTruckDto.getOperatingHours());
                    foodTruck.setLocation(foodTruckDto.getLocation());
                    return foodTruckRepository.save(foodTruck);
                })
                .orElseGet(() -> {
                    FoodTruck newFoodTruck = new FoodTruck();
                    newFoodTruck.setCode(truckCode);
                    FoodTruckOwner owner = foodTruckOwnerRepository.findById(foodTruckDto.getOwnerId()).orElseThrow(
                            () -> new FoodTruckOwnerNotFoundException(foodTruckDto.getOwnerId()));
                    newFoodTruck.setOwner(owner);
                    newFoodTruck.setLocation(foodTruckDto.getLocation());
                    newFoodTruck.setOperatingHours(foodTruckDto.getOperatingHours());
                    newFoodTruck.setName(foodTruckDto.getName());
                    return foodTruckRepository.save(newFoodTruck);
                });
    }

    @GetMapping("/foodtrucks/byOwnerId/{ownerId}")
    List<FoodTruck> getFoodTrucksByOwnerId(@PathVariable("ownerId") Long ownerId) {
        return foodTruckRepository.findFoodTruckBy_OwnerId(ownerId);
    }


    @DeleteMapping("/foodtrucks/{code}")
    void deleteFoodTruck(@PathVariable("code") String truckCode) {
        foodTruckRepository.deleteById(truckCode);
    }

}
