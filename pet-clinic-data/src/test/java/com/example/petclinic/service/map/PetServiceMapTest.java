package com.example.petclinic.service.map;

import com.example.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
    void deleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();
        petServiceMap.delete(pet);

        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void deleteWithNullId() {
        Pet pet = Pet.builder().build();
        petServiceMap.delete(pet);

        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void deleteNull() {
        petServiceMap.delete(null);

        assertEquals(1, petServiceMap.findAll().size());
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
    void saveDuplicate() {
        Long id = 1L;
        Pet pet2 = Pet.builder().id(id).build();
        Pet savedPet = petServiceMap.save(pet2);

        assertEquals(id, savedPet.getId());
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void findByExistingId() {
        Pet pet = petServiceMap.findById(id);

        assertEquals(id, pet.getId());
    }

    @Test
    void findByNotExistingId() {
        Pet pet = petServiceMap.findById(2L);

        assertNull(pet);
    }

    @Test
    void findByIdIsNull() {
        Pet pet = petServiceMap.findById(null);

        assertNull(pet);
    }
}