package com.example.cms.controller.exceptions;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException(Long id) {
        super("Could not find favorite " + id);
    }
}
