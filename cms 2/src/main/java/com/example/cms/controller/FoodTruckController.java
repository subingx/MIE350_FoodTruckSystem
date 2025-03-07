package com.example.cms.controller;

import com.example.cms.controller.dto.FoodTruckDto;
import com.example.cms.controller.exceptions.FoodTruckOwnerNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.model.entity.*;
import com.example.cms.model.repository.*;

import com.example.cms.model.repository.FoodTruckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FoodTruckController {
    @Autowired
    private final FoodTruckRepository foodTruckRepository;

    @Autowired
    private FoodTruckOwnerRepository ownerRepository;

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
    FoodTruck createFoodTruck(@RequestBody FoodTruckDto FoodTruckDto) {
        FoodTruck newFoodTruck = new FoodTruck();
        newFoodTruck.setName(FoodTruckDto.getName());
        newFoodTruck.setCode(FoodTruckDto.getCode());
        FoodTruckOwner owner = ownerRepository.findById(FoodTruckDto.getOwnerId()).orElseThrow(
                () -> new FoodTruckOwnerNotFoundException(FoodTruckDto.getOwnerId()) );
        newFoodTruck.setOwner(owner);
        return foodTruckRepository.save(newFoodTruck);
    }


    @DeleteMapping("/foodtrucks/{code}")
    void deleteFoodTruck(@PathVariable("code") String truckCode) {
        foodTruckRepository.deleteById(truckCode);
    }

}
