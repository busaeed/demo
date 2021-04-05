package com.alt6wer.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    User findById(int id);
    List<User> findAll();
    void deleteById(int id);

}
