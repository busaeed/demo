package com.alt6wer.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Topic;

public interface TopicRepositroy extends CrudRepository<Topic, Integer> {
    
    Topic findById(int topicId);

}
