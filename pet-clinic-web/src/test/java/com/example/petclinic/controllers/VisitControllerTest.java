package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.model.Visit;
import com.example.petclinic.service.PetService;
import com.example.petclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    VisitService visitService;
    @Mock
    PetService petService;
    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    Visit visit;
    Owner owner;
    Pet pet;

    @BeforeEach
    void setUp() {
        visit = Visit.builder().id(1L).build();
        owner = Owner.builder().id(1L).build();
        pet = Pet.builder().id(1L).build();
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

    }

    @Test
    void initVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/addOrUpdateVisitForm"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attribute("pet", petService.findById(pet.getId())));
    }

    @Test
    void processVisitForm() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/pets/1/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2020-11-11")
                .param("description", "description"))

                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(visitService).save(any());
    }


}