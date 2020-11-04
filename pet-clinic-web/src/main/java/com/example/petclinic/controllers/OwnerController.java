package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @GetMapping("/ownersList")
    public String allOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/ownersList";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");

        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }

    @GetMapping("/find")
    public String findOwnerForm(Model model) {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping()
    public String findOwnerResult(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        Set<Owner> owners = ownerService.findByLastNameLike("%" + owner.getLastName() + "%");

        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "/owners/findOwners";
        } else if (owners.size() == 1) {
            owner = owners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "/owners/ownersList";
        }
    }
}
