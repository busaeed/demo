package com.alt6wer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Category;
import com.alt6wer.demo.model.Forum;
import com.alt6wer.demo.repository.ForumRepository;

@Service
public class ForumService {
    
    @Autowired
    private ForumRepository forumRepository;
    
    @Autowired
    private CategoryService categoryService;
    
    public Forum createForum(Forum forum, int categoryId) {
        Category category = categoryService.findById(categoryId);
        forum.setCategory(category);
        return forumRepository.save(forum);
    }

    public Forum findById(int forumId) {
        return forumRepository.findById(forumId);
    }

}
