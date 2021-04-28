package com.alt6wer.demo.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.ResetPassword;
import com.alt6wer.demo.model.Role;
import com.alt6wer.demo.repository.MemberRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private RoleService roleService;
    
    public Member createMember(Member member) {
        member.setHashedPassword(this.hashPassword(member.getPassword()));
        member.setRegisteredAt(this.getCurrentLocalDateTime());
        member.setVerificationCode(RandomString.make(64));
        //user.setLastActivity(currentDateTime.toEpochSecond(ZoneOffset.UTC));
        //member.setActive(true);
        if (member.getRole() == null) {
        	member.setRole(this.getDefaultRole());
        }
        return memberRepository.save(member);
    }
    
    private String hashPassword(final String rawPassword) {
    	return new BCryptPasswordEncoder().encode(rawPassword);
    }
    
    private LocalDateTime getCurrentLocalDateTime() {
    	return LocalDateTime.now(ZoneOffset.UTC);
    }
    
    private Role getDefaultRole() {
    	return roleService.findByRoleName("ROLE_USER");
    }

    @Cacheable(value="member", key="#memberId")
    public Member findById(int memberId) {
        return memberRepository.findById(memberId);
    }
    
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    
    public Member findByVerificationCode(String verificationCode) {
    	return memberRepository.findByVerificationCode(verificationCode);
    }
    
    public Member verifyMemberEmail(String verificationCode) {
    	Member member = memberRepository.findByVerificationCode(verificationCode);
    	if (member == null || member.isActive()) {
    		return null;
    	}
    	member.setVerificationCode(null);
    	member.setActive(true);
    	return memberRepository.save(member);
    }

	public void createResetPasswordToken(Member member) {
		if (member == null) {
			return;
		}
		member.setResetPasswordToken(RandomString.make(64));
		memberRepository.save(member);
	}

	public Member verifyMemberResetPasswordToken(String resetPasswordToken) {
		Member member = memberRepository.findByResetPasswordToken(resetPasswordToken);
		if (member == null) {
			return null;
		}
		member.setActive(true);
		return member;
	}

	public void resetMemberPassword(ResetPassword resetPassword) {
		Member member = this.verifyMemberResetPasswordToken(resetPassword.getResetPasswordToken());
		member.setVerificationCode(null);
		member.setResetPasswordToken(null);
		member.setHashedPassword(this.hashPassword(resetPassword.getPassword()));
		memberRepository.save(member);
	}
	
	public List<Member> findOnlineMembers() {
		long lastAllowedSecond = (Instant.now().getEpochSecond()*1000)-20000;
		return memberRepository.findOnlineMembers(lastAllowedSecond);
	}
	
	public Member save(Member member) {
		return memberRepository.save(member);
	}
	
	public int findTotalNumberOfMembers() {
		return memberRepository.countBy();
	}
	
	public int findTotalNumberOfActiveMembers(long activeSince) {
		return memberRepository.countByLastActivityGreaterThan(activeSince);
	}
	
}
