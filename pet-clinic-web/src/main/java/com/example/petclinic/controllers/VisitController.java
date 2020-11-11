package com.example.petclinic.controllers;

import com.example.petclinic.model.Visit;
import com.example.petclinic.service.PetService;
import com.example.petclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;


    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

//    @ModelAttribute("visit")
//    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
//        Pet pet = petService.findById(petId);
//        model.addAttribute("pet", pet);
//        Visit visit = new Visit();
//        pet.getVisits().add(visit);
//
//        return visit;
//    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initVisitForm(@PathVariable Long petId, Model model) {
        model.addAttribute("visit", new Visit());
        model.addAttribute("pet", petService.findById(petId));
        return "pets/addOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processVisitForm(@Valid Visit visit, BindingResult bindingResult,
                                   @PathVariable Long ownerId, @PathVariable Long petId) {
        if (bindingResult.hasErrors()) {
            return "pets/addOrUpdateVisitForm";
        } else {
            visit.setPet(petService.findById(petId));
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }


}
