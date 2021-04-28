package com.alt6wer.demo.authentication;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.Session;
import com.alt6wer.demo.service.MemberService;
import com.alt6wer.demo.service.SessionService;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SessionService sessionService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		UserPrincipal principal = (UserPrincipal) event.getAuthentication().getPrincipal();
		
		String username = principal.getUsername();
		String sessionId = httpSession.getId();
		
		Member member = memberService.findByUsername(username);
		Session session = sessionService.findBySessionId(sessionId);
		
		session.setMember(member);
		sessionService.save(session);
	}

}
