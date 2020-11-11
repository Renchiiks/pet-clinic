package com.example.petclinic.service.springdatajpa;

import com.example.petclinic.model.Visit;
import com.example.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJPAServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJPAService service;

    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        visits.add(Visit.builder().id(1L).build());
        visits.add(Visit.builder().id(2L).build());

        Mockito.when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> savedVisits = service.findAll();

        assertEquals(2, savedVisits.size());

        verify(visitRepository).findAll();
    }

//    @Test
//    void findById() {
//        when(visitRepository.findById(any())).thenReturn(Optional.of(returnVisit));
//
//        Visit visit = Visit.builder().id(1L).build();
//
//        assertNotNull(visit);
//    }
//
//    @Test
//    void findByIdNotFound() {
//        when(visitRepository.findById(any())).thenReturn(Optional.empty());
//
//        Visit visit = Visit.builder().id(1L).build();
//
//        assertNull(visit);
//    }

    @Test
    void save() {
        Visit visit = Visit.builder().id(1L).build();

        when(visitRepository.save(any())).thenReturn(returnVisit);

        Visit savedVisit = service.save(visit);

        assertNotNull(savedVisit);

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVisit);

        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(visitRepository).deleteById(anyLong());

    }
}