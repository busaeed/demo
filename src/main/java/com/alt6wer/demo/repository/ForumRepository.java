package com.alt6wer.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Forum;

public interface ForumRepository extends CrudRepository<Forum, Integer> {
    
    Forum findById(int forumId);
    
    
    
}
