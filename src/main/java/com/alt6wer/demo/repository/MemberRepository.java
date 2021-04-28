package com.alt6wer.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    
    Member findById(int userId);
    
    Member findByUsername(String username);
    
    Member findByEmail(String email);
    
	Member findByVerificationCode(String verificationCode);

	Member findByResetPasswordToken(String resetPasswordToken);
	
	@Query("select NEW com.alt6wer.demo.model.Member (m.id, m.username) from Member m join m.session s where s.lastAccessTime > ?1")
	List<Member> findOnlineMembers(long lastAllowedSecond);
	
	int countBy();
	
	int countByLastActivityGreaterThan(long activeSince);
    
}
