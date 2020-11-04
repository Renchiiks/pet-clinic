package com.example.petclinic.service;

import com.example.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    Set<Owner> findByLastNameLike(String lastName);
}
