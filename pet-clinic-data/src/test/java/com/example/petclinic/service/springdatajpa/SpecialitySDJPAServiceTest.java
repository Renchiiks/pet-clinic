package com.example.petclinic.service.springdatajpa;

import com.example.petclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJPAServiceTest {
    @Mock
    Speciality specialityRepository;

    @InjectMocks
    SpecialitySDJPAService service;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().id(1L).build();
    }

    @Test
    void findAll() {
//        Set<Speciality> specialities = new HashSet<>();
//        specialities.add(Speciality.builder().id(1L).build());
//        specialities.add(Speciality.builder().id(2L).build());
//
//        when(specialityRepository.findAll()).thenReturn(specialities);
//        Set<Speciality> savedList = service.findAll();
//
//        assertEquals(2, savedList.size());
//
//        verify(specialityRepository).findAll();

    }

    @Test
    void findById() {

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}