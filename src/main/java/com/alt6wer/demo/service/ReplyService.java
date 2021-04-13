package com.alt6wer.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Reply;
import com.alt6wer.demo.repository.ReplyRepository;

@Service
public class ReplyService {
    
    @Autowired
    private ReplyRepository replyRepository;

    public Reply createReply(Reply reply) {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        reply.setCreatedAt(currentDateTime);
        return replyRepository.save(reply);
    }

}
