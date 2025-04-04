package com.example.cms.controller.exceptions;

public class FoodTruckOwnerAlreadyExistsException extends RuntimeException {
    public FoodTruckOwnerAlreadyExistsException(Long id) {
        super("User with id " + id + " already exists");
    }
}
