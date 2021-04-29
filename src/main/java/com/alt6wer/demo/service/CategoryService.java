package com.alt6wer.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Category;
import com.alt6wer.demo.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category insert(Category category) {
        return categoryRepository.save(category);
    }
    
    public Category update(Category category) {
    	return null;
    }
    
    public boolean delete(Category category) {
    	return false;
    }

    public Category findById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Cacheable("categories")
	public List<Category> findAll() {
		return categoryRepository.findAllByOrderByOrderingAsc();
	}

}
