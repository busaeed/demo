package com.alt6wer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Role;
import com.alt6wer.demo.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
    
    public Role findById(int id) {
        return roleRepository.findById(id);
    }

}
