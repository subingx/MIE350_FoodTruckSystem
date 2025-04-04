package com.example.cms.controller.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(Long id) {
        super("User with id " + id + " already exists");
    }
}
