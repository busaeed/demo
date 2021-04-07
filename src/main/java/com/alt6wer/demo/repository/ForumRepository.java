package com.alt6wer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alt6wer.demo.entity.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer> {

}
