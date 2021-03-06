package com.example.petclinic.service.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VisitServiceMapTest {
    VisitServiceMap visitServiceMap;
    Long id = 1L;
    Owner owner = Owner.builder().id(id).build();
    Pet pet = Pet.builder().owner(owner).id(id).build();

    @BeforeEach
    void setUp() {
        visitServiceMap = new VisitServiceMap();

        Visit visit = Visit.builder().id(id).pet(pet).build();

        visitServiceMap.save(visit);
    }

    @Test
    void findAll() {
        Set<Visit> visits = visitServiceMap.findAll();

        assertEquals(1, visits.size());
    }

    @Test
    void deleteById() {
        visitServiceMap.deleteById(id);

        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void delete() {
        visitServiceMap.delete(visitServiceMap.findById(id));

        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void saveWithExistingId() {
        Long id = 2L;
        Visit visit = Visit.builder().id(id).pet(pet).build();

        Visit savedVisit = visitServiceMap.save(visit);

        assertEquals(id, savedVisit.getId());
    }

    @Test
    void saveWithNoId() {
        Visit visit = Visit.builder().pet(pet).build();
        visitServiceMap.save(visit);

        assertNotNull(visit);
        assertNotNull(visit.getId());
    }

    @Test
    void findById() {
        Visit visit = visitServiceMap.findById(id);

        assertEquals(id, visit.getId());
    }
}