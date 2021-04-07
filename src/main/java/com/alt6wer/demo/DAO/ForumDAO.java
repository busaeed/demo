package com.alt6wer.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.repository.ForumRepository;

@Service
public class ForumDAO {
    
    @Autowired
    private ForumRepository forumRepository;

}
