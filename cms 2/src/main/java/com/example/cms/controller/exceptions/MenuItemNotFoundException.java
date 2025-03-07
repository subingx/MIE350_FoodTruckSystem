package com.example.cms.controller.exceptions;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String code) {
        super("Could not find menu item " + code);}
}
