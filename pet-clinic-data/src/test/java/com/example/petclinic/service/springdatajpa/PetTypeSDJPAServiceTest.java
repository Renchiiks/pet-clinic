package com.example.petclinic.service.springdatajpa;

import com.example.petclinic.model.PetType;
import com.example.petclinic.repositories.PetTypeRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJPAServiceTest {
    @Mock
    PetTypeRepository repository;

    @InjectMocks
    PetTypeSDJPAService service;

    PetType retrunPetType;

    @BeforeEach
    void setUp() {
        retrunPetType = PetType.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).build());
        petTypes.add(PetType.builder().id(2L).build());

        Mockito.when(repository.findAll()).thenReturn(petTypes);

        Set<PetType> checkList = service.findAll();

        assertEquals(2, checkList.size());

        verify(repository).findAll();
    }

    @Test
    void findById() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(retrunPetType));

        PetType petType = service.findById(1L);

        assertNotNull(petType);
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(1L);

        assertNull(petType);
    }

    @Test
    void save() {
        PetType petType = PetType.builder().id(1L).build();

        when(repository.save(any())).thenReturn(retrunPetType);

        PetType savedType = service.save(petType);

        assertNotNull(savedType);

        verify(repository).save(any());
    }

    @Test
    void delete() {
        service.delete(retrunPetType);

        verify(repository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(repository).deleteById(anyLong());
    }
}