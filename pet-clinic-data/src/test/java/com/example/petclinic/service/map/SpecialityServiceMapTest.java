package com.example.petclinic.service.map;

import com.example.petclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Set<Speciality> specialities = new HashSet<>();

        assertEquals(1, specialityServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        specialityServiceMap.deleteById(id);

        assertEquals(0, specialityServiceMap.findAll().size());
    }

    @Test
    void delete() {
        specialityServiceMap.delete(specialityServiceMap.findById(id));

        assertEquals(0, specialityServiceMap.findAll().size());
    }

    @Test
    void saveWithNoId() {
        Speciality speciality = Speciality.builder().build();
        specialityServiceMap.save(speciality);

        assertNotNull(speciality);
        assertNotNull(speciality.getId());
    }

    @Test
    void saveWithExistingId() {
        Speciality speciality2 = Speciality.builder().id(2L).build();
        Speciality savedSpeciality = specialityServiceMap.save(speciality2);

        assertEquals(id, savedSpeciality.getId());
    }

    @Test
    void findById() {
        Speciality speciality = specialityServiceMap.findById(id);

        assertEquals(id, speciality.getId());
    }
}