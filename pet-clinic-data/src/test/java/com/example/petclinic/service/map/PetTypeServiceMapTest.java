package com.example.petclinic.service.map;

import com.example.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeServiceMapTest {

    PetTypeServiceMap petTypeServiceMap;

    Long id = 1L;

    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        PetType petType = PetType.builder().id(id).build();
        petTypeServiceMap.save(petType);
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeServiceMap.findAll();

        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(id);

        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(id));

        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void savePetWithExistingId() {
        Long id = 2L;
        PetType petType = PetType.builder().id(id).build();
        PetType savedPetType = petTypeServiceMap.save(petType);

        assertEquals(id, savedPetType.getId());
    }

    @Test
    void savePetWithNoId() {
        PetType petType = PetType.builder().build();
        petTypeServiceMap.save(PetType.builder().build());

        petTypeServiceMap.save(petType);

        assertNotNull(petType);
        assertNotNull(petType.getId());
        assertNotNull(petTypeServiceMap);

    }

    @Test
    void findById() {
        PetType petType = petTypeServiceMap.findById(id);

        assertEquals(id, petType.getId());
    }
}