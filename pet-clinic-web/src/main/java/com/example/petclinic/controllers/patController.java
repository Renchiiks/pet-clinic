package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.PetType;
import com.example.petclinic.service.OwnerService;
import com.example.petclinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class patController {

    private final PetTypeService petTypeService;
    private final OwnerService ownerService;


    public patController(PetTypeService petTypeService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("type")
    public Collection<PetType> petTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("ownerId")
    public Owner owner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
