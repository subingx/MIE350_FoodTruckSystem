package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.ArrayList;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "food_trucks")
public class FoodTruck {

    @Id
    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String location;

    @NotEmpty
    private String operatingHours;

    @ManyToOne
    @JoinColumn(name="ownerId")
    private FoodTruckOwner owner;

    @OneToMany(mappedBy = "foodTruck")
    private List<MenuItem> menuItems = new ArrayList<>();

    public FoodTruck(String code, String name, String location, String operatingHours, FoodTruckOwner owner){
        this.code = code;
        this.name = name;
        this.location = location;
        this.operatingHours = operatingHours;
        this.owner = owner;
    }

}
