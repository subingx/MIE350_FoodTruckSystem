package com.example.cms.controller;

import com.example.cms.controller.dto.MenuItemDto;
import com.example.cms.controller.exceptions.FoodTruckNotFoundException;
import com.example.cms.controller.exceptions.MenuItemNotFoundException;
import com.example.cms.model.entity.FoodTruckOwner;
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
    MenuItem createMenuItem(@RequestBody MenuItemDto menuItemDto) {
        MenuItem newMenuItem = new MenuItem();
        FoodTruck foodTruck = foodTruckRepository.findById(menuItemDto.getTruckCode())
                .orElseThrow(() -> new FoodTruckNotFoundException(menuItemDto.getTruckCode()));

        newMenuItem.setCode(menuItemDto.getItemCode());
        newMenuItem.setName(menuItemDto.getName());
        newMenuItem.setPrice(menuItemDto.getPrice());
        newMenuItem.setAvailable(menuItemDto.getIsAvailable());
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

    @PutMapping("/menuitems/{code}")
    MenuItem updateMenuItem(@RequestBody MenuItemDto menuItemDto, @PathVariable("code") String itemCode) {
        return repository.findById(itemCode)
                .map(item -> {
                    item.setName(menuItemDto.getName());
                    item.setPrice(menuItemDto.getPrice());
                    item.setAvailable(menuItemDto.getIsAvailable());

                    if (menuItemDto.getTruckCode() != null) {
                        FoodTruck foodTruck = foodTruckRepository.findById(menuItemDto.getTruckCode())
                                .orElseThrow(() -> new FoodTruckNotFoundException(menuItemDto.getTruckCode()));
                        item.setFoodTruck(foodTruck);
                    }

                    return repository.save(item);
                })
                .orElseGet(() -> {
                    FoodTruck foodTruck = foodTruckRepository.findById(menuItemDto.getTruckCode())
                            .orElseThrow(() -> new FoodTruckNotFoundException(menuItemDto.getTruckCode()));

                    MenuItem newMenuItem = new MenuItem();

                    newMenuItem.setCode(itemCode);
                    newMenuItem.setName(itemCode);
                    newMenuItem.setPrice(menuItemDto.getPrice());
                    newMenuItem.setAvailable(menuItemDto.getIsAvailable());
                    newMenuItem.setFoodTruck(foodTruck);

                    return repository.save(newMenuItem);


                });
    }
    @GetMapping("/menuitems/byTruck/{truckCode}")
    List<MenuItem> retrieveMenuItemsByTruck(@PathVariable("truckCode") String truckCode) {
        return menuItemRepository.findByTruckCode(truckCode);
    }



}
