package com.example.cms.controller;

import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.controller.exceptions.MenuItemNotFoundException;
import com.example.cms.model.entity.MenuItem;
import com.example.cms.model.entity.FoodTruck;
import com.example.cms.model.repository.MenuItemRepository;
import com.example.cms.model.repository.FoodTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MenuItemController {
    @Autowired
    private final MenuItemRepository repository;

    @Autowired
    private FoodTruckRepository foodTruckRepository;

    public MenuItemController(MenuItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/menuitems")
    List<MenuItem> retrieveAllMenuItems() {
        return repository.findAll();
    }

    @PostMapping("/menuitems")
    MenuItem createMenuItem(@RequestBody MenuItem newMenuItem) {
        FoodTruck foodTruck = foodTruckRepository.findById(newMenuItem.getFoodTruck().getCode()).orElseThrow(
                () -> new FoodTruckNotFoundException(newMenuItem.getFoodTruck().getCode()));
        newMenuItem.setFoodTruck(foodTruck);
        return repository.save(newMenuItem);
    }

    @GetMapping("/menuitems/{code}")
    MenuItem retrieveMenuItem(@PathVariable("code") String itemCode) {
        return repository.findById(itemCode)
                .orElseThrow(() -> new MenuItemNotFoundException(itemCode));
    }


    @DeleteMapping("/menuitems/{code}")
    void deleteMenuItem(@PathVariable("code") String itemCode) {
        repository.deleteById(itemCode);
    }
}
