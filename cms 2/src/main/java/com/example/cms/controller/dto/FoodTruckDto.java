package com.example.cms.controller.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FoodTruckDto {
    private String code;
    private String name;
    private Long ownerId;
    private String location;
    private String operatingHours;

}
