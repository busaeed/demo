package com.alt6wer.demo.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.alt6wer.demo.model.ForgetPassword;
import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.ResetPassword;
import com.alt6wer.demo.service.EmailService;
import com.alt6wer.demo.service.MemberService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailService emailService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/forget-password")
    public String forgetPassword(Model model) {
    	ForgetPassword forgetPassword = new ForgetPassword();
    	model.addAttribute("forgetPassword", forgetPassword);
    	return "forget-password";
    }
    
    @PostMapping("/forget-password")
    public String forgetPasswordHandling(@Valid @ModelAttribute("forgetPassword") ForgetPassword forgetPassword, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "forget-password";
    	}
    	Member member = memberService.findByEmail(forgetPassword.getEmail());
    	memberService.createResetPasswordToken(member);
    	try {
			emailService.sendResetPasswordEmail(member);
		} catch (UnsupportedEncodingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encoding Problem", e);
		} catch (MessagingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message Problem", e);
		}
    	model.addAttribute("email", forgetPassword.getEmail());
    	return "forget-password2";
    }
    
    @GetMapping("/reset-password")
    public String resetPassword(@Param("resetPasswordToken") String resetPasswordToken, Model model) {
    	if (resetPasswordToken == null) {
    		return "reset_password_failure";
    	}
    	Member member = memberService.verifyMemberResetPasswordToken(resetPasswordToken);
    	if (member == null) {
    		return "reset_password_failure";
    	}
    	ResetPassword resetPassword = new ResetPassword();
    	model.addAttribute("resetPassword", resetPassword);
    	model.addAttribute("token", resetPasswordToken);
    	return "reset-password";
    }
    
    @PostMapping("/reset-password")
    public String resetPasswordHandling(@Valid @ModelAttribute("resetPassword") ResetPassword resetPassword, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "reset-password";
    	}
    	memberService.resetMemberPassword(resetPassword);
    	return "reset-password2";
    }
    
    
    @GetMapping("/register")
    public String registration(Model model) {
    	Member member = new Member();
    	model.addAttribute("member", member);
    	return "register";
    }
    
    @PostMapping("/register")
    public String registerationHandling(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "register";
    	}
    	Member createdMember = memberService.createMember(member);
    	model.addAttribute("email", createdMember.getEmail());
    	try {
			emailService.sendVerificationEmail(createdMember);
		} catch (UnsupportedEncodingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Encoding Problem", e);
		} catch (MessagingException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message Problem", e);
		}
		return "register2";
    }
    
    @GetMapping("/verifyEmail")
    public String verifyMember(@Param("verificationCode") String verificationCode, Model model) {
    	if (verificationCode == null) {
    		return "verification_failure";
    	}
    	Member member = memberService.verifyMemberEmail(verificationCode);
    	if (member == null) {
    		return "verification_failure";
    	}
    	model.addAttribute("username", member.getUsername());
    	return "verification_sucess";
    }

}
