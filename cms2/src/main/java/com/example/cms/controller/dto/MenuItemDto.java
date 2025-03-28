package com.example.cms.controller.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MenuItemDto {
    private String name;
    private Double price;
    private Boolean isAvailable;
    private String truckCode;

}
