package com.example.petclinic.service.map;

import com.example.petclinic.model.Pet;
import com.example.petclinic.service.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet obj) {
        super.delete(obj);
    }

    @Override
    public Pet save(Pet obj) {
        return super.save( obj);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
