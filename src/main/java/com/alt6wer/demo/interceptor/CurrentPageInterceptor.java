package com.alt6wer.demo.interceptor;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.Session;
import com.alt6wer.demo.service.MemberService;
import com.alt6wer.demo.service.SessionService;

public class CurrentPageInterceptor implements HandlerInterceptor {
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MemberService memberService;
		
	//private final Logger log = LoggerFactory.getLogger(CurrentPageInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//log.info("preHandle from GeneralInterceptor");
		HandlerMethod routeMethod = (HandlerMethod) handler;
        GetMapping mapping = routeMethod.getMethodAnnotation(GetMapping.class);
        String currentPage;
        if (mapping != null) {
        	currentPage = String.join("", mapping.value());
        } else {
        	currentPage = null;
        }
        String sessionId = request.getRequestedSessionId();
        Session session = sessionService.findBySessionId(sessionId);
        if (session != null) {
        	session.setCurrentPage(currentPage);
        	//The following line to update this column because it doesn't get updated early
        	//because I need this column in visitor counter
        	session.setLastAccessTime(Instant.now().getEpochSecond()*1000);
            sessionService.save(session);
            //the following lines to update member last_activity
            Member member = session.getMember();
            if (member != null) {
            	member.setLastActivity(Instant.now().getEpochSecond()*1000);
            	memberService.save(member);
            }
        }
		return true; //if false controller and other 2 methods will not be invoked as well as route
	}

	/*
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("postHandle from GeneralInterceptor");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("afterCompletion from GeneralInterceptor");
	}*/
	

}
