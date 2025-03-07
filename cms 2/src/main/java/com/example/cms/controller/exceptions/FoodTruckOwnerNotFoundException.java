package com.example.cms.controller.exceptions;

public class FoodTruckOwnerNotFoundException extends RuntimeException{
    public FoodTruckOwnerNotFoundException (Long id) {super("Could not find food truck owner " + id);}
}
