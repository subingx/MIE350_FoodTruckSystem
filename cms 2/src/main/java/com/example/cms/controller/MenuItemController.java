package com.example.cms.controller;

import com.example.cms.controller.exceptions.MenuItemNotFoundException;
import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.model.entity.MenuItem;
import com.example.cms.model.entity.FoodTruck;
import com.example.cms.model.repository.MenuItemRepository;
import com.example.cms.model.repository.FoodTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/menuitems")
public class MenuItemController {

    @Autowired
    private final MenuItemRepository repository;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    public MenuItemController(MenuItemRepository repository) {
        this.repository = repository;
    }

    // Retrieve all menu items
    @GetMapping
    List<MenuItem> retrieveAllMenuItems() {
        return repository.findAll();
    }

    // Create a new menu item
    @PostMapping
    MenuItem createMenuItem(@RequestBody MenuItem newMenuItem) {
        // Fetch the associated FoodTruck
        if (newMenuItem.getFoodTruck() != null && newMenuItem.getFoodTruck().getId() != null) {
            FoodTruck foodTruck = foodTruckRepository.findById(newMenuItem.getFoodTruck().getId())
                    .orElseThrow(() -> new FoodTruckNotFoundException(newMenuItem.getFoodTruck().getId()));
            newMenuItem.setFoodTruck(foodTruck);
        }
        return repository.save(newMenuItem);
    }

    // Retrieve a specific menu item by code
    @GetMapping("/{code}")
    MenuItem retrieveMenuItem(@PathVariable("code") String code) {
        return repository.findById(code)
                .orElseThrow(() -> new MenuItemNotFoundException(code));
    }


    // Delete a menu item by ID
    @DeleteMapping("/{code}")
    void deleteMenuItem(@PathVariable("code") String code) {
        repository.deleteById(code);
    }
}