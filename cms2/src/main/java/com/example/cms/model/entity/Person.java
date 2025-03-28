package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass // Add this annotation
public abstract class Person {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate IDs
    private long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;



}
