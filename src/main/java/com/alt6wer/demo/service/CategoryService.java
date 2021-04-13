package com.alt6wer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Category;
import com.alt6wer.demo.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

}
