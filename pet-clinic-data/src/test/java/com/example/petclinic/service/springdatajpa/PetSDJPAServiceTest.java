package com.example.petclinic.service.springdatajpa;

import com.example.petclinic.model.Pet;
import com.example.petclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PetSDJPAServiceTest {
    @Mock
    PetRepository repository;

    @InjectMocks
    PetSDJPAService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPets = new HashSet<>();
        returnPets.add(Pet.builder().id(1L).build());
        returnPets.add(Pet.builder().id(2L).build());

        Mockito.when(repository.findAll()).thenReturn(returnPets);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());

    }

    @Test
    void findById() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(1L);

        assertNotNull(pet);

    }

    @Test
    void findByIdWhenNull() {

        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        Pet pet = service.findById(1L);

        assertNull(pet);
    }

    @Test
    void save() {
        Pet pet = Pet.builder().id(1L).build();

        Mockito.when(repository.save(any())).thenReturn(returnPet);

        Pet savedPet = service.save(pet);

        assertNotNull(savedPet);

        verify(repository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);

        verify(repository).delete(any());
    }

    @Test
    void deleteById() {
        service.delete(service.findById(1L));

        verify(repository).findById(anyLong());
    }
}