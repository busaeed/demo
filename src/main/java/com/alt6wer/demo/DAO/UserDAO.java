package com.alt6wer.demo.DAO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.entity.User;
import com.alt6wer.demo.repository.UserRepository;

@Service
public class UserDAO {
    
    @Autowired
    private UserRepository userRepository;
    
    public String addNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        user.setRegisterationDate(currentDateTime);
        user.setLastActivity(currentDateTime.toEpochSecond(ZoneOffset.UTC));
        return userRepository.save(user).toString();
    }

}
