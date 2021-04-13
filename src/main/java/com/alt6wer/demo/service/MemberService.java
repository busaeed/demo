package com.alt6wer.demo.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.repository.MemberRepository;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    public Member createMember(Member member) {
        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
        member.setRegisteredAt(currentDateTime);
        //user.setLastActivity(currentDateTime.toEpochSecond(ZoneOffset.UTC));
        //member.setActive(true);
        return memberRepository.save(member);
    }

    public Member findById(int memberId) {
        return memberRepository.findById(memberId);
    }
    
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
