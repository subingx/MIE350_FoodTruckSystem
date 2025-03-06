package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "foodTruckId")
    private FoodTruck foodTruck;

    public MenuItem(String name, Double price, FoodTruck foodTruck) {
        this.name = name;
        this.price = price;
        this.foodTruck = foodTruck;
    }
}
