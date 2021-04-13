package com.alt6wer.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Topic;
import com.alt6wer.demo.repository.TopicRepositroy;

@Service
public class TopicService {
    
    @Autowired
    private TopicRepositroy topicRepository;

    public Topic createTopic(Topic topic) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        topic.setCreatedAt(currentDateTime);
        return topicRepository.save(topic);
    }

    public Topic findById(int topicId) {
        return topicRepository.findById(topicId);
    }

}
