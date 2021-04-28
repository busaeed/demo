package com.alt6wer.demo.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Session;
import com.alt6wer.demo.repository.MySessionRepository;

@Service
public class SessionService {
	
	@Autowired
	private MySessionRepository sessionRepository;
	
	public Session findBySessionId(String sessionId) {
		return sessionRepository.findBySessionId(sessionId);
	}
	
	public int findNumberOfOnlineGuests() {
		long lastAllowedSecond = (Instant.now().getEpochSecond()*1000)-20000;
		return sessionRepository.countByLastAccessTimeGreaterThanAndPrincipalNameIsNull(lastAllowedSecond);
	}
	
	public Session save(Session session) {
		return sessionRepository.save(session);
	}

}
