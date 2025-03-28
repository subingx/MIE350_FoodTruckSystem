package com.example.cms.controller.exceptions;

public class FoodTruckNotFoundException extends RuntimeException{
    public FoodTruckNotFoundException(String code) {
        super("Could not find food truck " + code);
    }
}
