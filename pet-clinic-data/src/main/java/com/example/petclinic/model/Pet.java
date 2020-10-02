package com.example.petclinic.model;

import java.util.Date;

public class Pet extends BaseEntity {
    private Owner owner;
    private PetType petType;
    private Date birthDate;
}
