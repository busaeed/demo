package com.alt6wer.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
    Category findById(int id);
    
    List<Category> findAllByOrderByOrderingAsc();

}
