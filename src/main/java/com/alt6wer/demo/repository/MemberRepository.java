package com.alt6wer.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    
    Member findById(int userId);
    Member findByUsername(String username);
    Member findByEmail(String email);
	Member findByVerificationCode(String verificationCode);
    
}
