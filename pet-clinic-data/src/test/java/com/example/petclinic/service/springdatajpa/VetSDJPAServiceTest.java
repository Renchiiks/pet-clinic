package com.example.petclinic.service.springdatajpa;

import com.example.petclinic.model.Vet;
import com.example.petclinic.repositories.VetRepository;
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
class VetSDJPAServiceTest {
    @Mock
    VetRepository vetRepository;
    @InjectMocks
    VetSDJPAService service;

    Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Vet> vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());

        Mockito.when(vetRepository.findAll()).thenReturn(vets);

        Set<Vet> savedVets = service.findAll();

        assertEquals(2, savedVets.size());
    }

    @Test
    void findById() {
        Mockito.when(vetRepository.findById(any())).thenReturn(Optional.of(returnVet));

        Vet savedVet = service.findById(1L);

        assertNotNull(savedVet);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(vetRepository.findById(any())).thenReturn(Optional.empty());

        Vet savedVet = service.findById(1L);

        assertNull(savedVet);
    }

    @Test
    void save() {
        Vet vet = Vet.builder().id(1L).build();

        Mockito.when(vetRepository.save(vet)).thenReturn(returnVet);

        Vet savedVet = service.save(vet);

        assertNotNull(savedVet);

        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVet);

        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(vetRepository).deleteById(anyLong());

    }
}