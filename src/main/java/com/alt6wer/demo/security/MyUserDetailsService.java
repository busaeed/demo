package com.alt6wer.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.service.MemberService;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberService.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException(email);
        }
        return new MyUserPrincipal(member);
    }

}
