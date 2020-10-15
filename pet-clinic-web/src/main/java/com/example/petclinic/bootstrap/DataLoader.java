package com.example.petclinic.bootstrap;

import com.example.petclinic.model.*;
import com.example.petclinic.service.OwnerService;
import com.example.petclinic.service.PetTypeService;
import com.example.petclinic.service.SpecialityService;
import com.example.petclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType dogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType catPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Street");
        owner1.setCity("City");
        owner1.setPhone("98765432");

        Pet michaelsDog = new Pet();
        michaelsDog.setPetType(dog);
        michaelsDog.setBirthDate(LocalDate.now());
        michaelsDog.setName("Reno");
        michaelsDog.setOwner(owner1);

        owner1.getPets().add(michaelsDog);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("98 Street");
        owner2.setCity("City");
        owner2.setPhone("12345678");

        Pet fionasPet = new Pet();
        fionasPet.setName("Tommy");
        fionasPet.setPetType(catPetType);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setOwner(owner2);

        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedDentistry = specialityService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedDentistry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedRadiology);

        vetService.save(vet2);

        System.out.println("Loaded Vets");
    }
}
