package com.alt6wer.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alt6wer.demo.entities.User;
import com.alt6wer.demo.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/all")
    public String getUsers() {
        return userRepository.findByUsername("gant").getEmail();
    }

}
