package com.example.petclinic.service.map;

import com.example.petclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialityServiceMapTest {

    SpecialityServiceMap specialityServiceMap;

    Long id = 1L;

    @BeforeEach
    void setUp() {
        specialityServiceMap = new SpecialityServiceMap();
        Speciality speciality = Speciality.builder().id(id).build();
        specialityServiceMap.save(speciality);
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }
}