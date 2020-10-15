package com.example.petclinic.service.map;

import com.example.petclinic.model.PetType;
import com.example.petclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeMap extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType obj) {
        super.delete(obj);
    }

    @Override
    public PetType save(PetType obj) {
        return super.save(obj);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}
