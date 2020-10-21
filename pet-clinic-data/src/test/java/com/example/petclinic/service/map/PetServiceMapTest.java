package com.example.petclinic.service.map;

import com.example.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetServiceMapTest {

    PetServiceMap petServiceMap;

    Long id = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();

        petServiceMap.save(Pet.builder().id(id).build());

    }

    @Test
    void findAll() {
        Set<Pet> pets = petServiceMap.findAll();

        assertEquals(1, pets.size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(id);

        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(id));

        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void savePetWithNoId() {
        Pet pet = Pet.builder().build();
        petServiceMap.save(pet);

        assertNotNull(pet);
        assertNotNull(pet.getId());
    }

    @Test
    void saveExistingPetId() {
        Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet2);

        assertEquals(id, savedPet.getId());

    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(id);

        assertEquals(id, pet.getId());
    }
}