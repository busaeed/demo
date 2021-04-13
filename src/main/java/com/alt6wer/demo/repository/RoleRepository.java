package com.alt6wer.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    
    Role findById(int id);

}
