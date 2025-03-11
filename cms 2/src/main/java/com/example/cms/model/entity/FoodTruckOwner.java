package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "foodtruckowners")
public class FoodTruckOwner extends Person {

    private String password;

    @OneToMany(mappedBy = "owner")
    @Nullable
    @JsonBackReference
    private List<FoodTruck> foodTrucks = new ArrayList<>();

}