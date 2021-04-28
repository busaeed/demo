package com.alt6wer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Forum;
import com.alt6wer.demo.repository.ForumRepository;

@Service
public class ForumService {
    
    @Autowired
    private ForumRepository forumRepository;
    
    public Forum createForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public Forum findById(int forumId) {
        return forumRepository.findById(forumId);
    }

}
