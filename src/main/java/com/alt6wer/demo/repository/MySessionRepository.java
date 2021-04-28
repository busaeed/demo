package com.alt6wer.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.Session;

public interface MySessionRepository extends CrudRepository<Session, String> {
	
	Session findBySessionId(String sessionId);
		
	List<Session> findByLastAccessTimeGreaterThan(long lastAllowedSecond);

	int countByLastAccessTimeGreaterThanAndPrincipalNameIsNull(long lastAllowedSecond);
	
}
