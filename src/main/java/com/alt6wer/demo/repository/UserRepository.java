package com.alt6wer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alt6wer.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
