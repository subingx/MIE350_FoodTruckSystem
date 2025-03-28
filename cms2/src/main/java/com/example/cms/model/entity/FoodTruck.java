package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.ArrayList;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "foodtrucks")
public class FoodTruck {

    @Id
    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    private String location;

    private String operatingHours;

    @ManyToOne
    @JoinColumn(name="ownerId", nullable = false)
    @JsonIgnoreProperties("foodTrucks")
    private FoodTruckOwner owner;

    @OneToMany(mappedBy = "foodTruck")
    private List<MenuItem> menuItems = new ArrayList<>();

//    public FoodTruck(String code, String name, String location, String operatingHours, FoodTruckOwner owner){
//        this.code = code;
//        this.name = name;
//        this.location = location;
//        this.operatingHours = operatingHours;
//        this.owner = owner;
//    }

}
