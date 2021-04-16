package com.alt6wer.demo.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.service.MemberService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private MemberService memberService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String registration(Model model) {
    	Member member = new Member();
    	model.addAttribute("member", member);
    	return "register";
    }
    
    @PostMapping("/register")
    public String registerationHandling(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult, Model model) throws UnsupportedEncodingException, MessagingException {
    	if (bindingResult.hasErrors()) {
    		return "register";
    	}
    	Member createdMember = memberService.createMember(member);
    	memberService.sendVerificationEmail(createdMember);
		model.addAttribute("email", createdMember.getEmail());
		return "register2";
    }
    
    @GetMapping("/verify")
    public String verifyMember(@Param("verificationCode") String verificationCode, Model model) {
    	Member member = memberService.verify(verificationCode);
    	if (member == null) {
    		return "verification_failure";
    	}
    	model.addAttribute("username", member.getUsername());
    	return "verification_sucess";
    }

}
