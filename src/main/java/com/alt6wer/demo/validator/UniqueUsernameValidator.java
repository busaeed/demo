package com.alt6wer.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alt6wer.demo.service.MemberService;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private MemberService memberService;
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
    	if (memberService.findByUsername(username) != null) {
    		return false;
    	}
		return true;
	}

}
