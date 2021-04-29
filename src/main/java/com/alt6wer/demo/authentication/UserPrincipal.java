package com.alt6wer.demo.authentication;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alt6wer.demo.model.Member;

public class UserPrincipal implements UserDetails {
    
    private Member member;

    public UserPrincipal(Member member) {
        this.member = member;
        
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(member.getRole().getRoleName());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return member.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return member.isActive();
    }
    
    //the following is explicitly defined method
    public int getId() {
    	return member.getId();
    }

}
