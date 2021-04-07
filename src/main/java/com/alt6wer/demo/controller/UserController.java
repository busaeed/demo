package com.alt6wer.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alt6wer.demo.entity.Forum;
import com.alt6wer.demo.repository.ForumRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    /*@Autowired
    private UserDAO userDAO;*/
    
    @Autowired
    private ForumRepository forumRepository;
    
    @GetMapping("/")
    public String index() {
        return "TEST";
    }
    
    @GetMapping("/all")
    public int page() {
        /*User user = new User("BU SAEED21", "bosaeed201160@gmail.com", "123456");
        return userDAO.addNewUser(user);*/
        List<Forum> forums = forumRepository.findAll();
        Forum forum = forums.isEmpty() ? null : forums.get(0);
        return forum.getId();
    }

}
