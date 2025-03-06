package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


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

    @No


    public Department(String code, String name, String contactEmail){
        this.code = code;
        this.name = name;
        this.contactEmail = contactEmail;
    }

}
