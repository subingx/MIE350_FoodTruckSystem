package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer extends Person {

    private String password;

    // Can add more attributes like phoneNumber...
}
