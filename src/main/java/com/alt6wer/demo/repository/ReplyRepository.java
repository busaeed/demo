package com.alt6wer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alt6wer.demo.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	int countBy();

}
