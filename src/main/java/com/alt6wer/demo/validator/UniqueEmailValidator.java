package com.alt6wer.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.service.MemberService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
	@Autowired
	private MemberService memberService;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		Member duplicatedEmail = memberService.findByEmail(email);
    	if (duplicatedEmail != null) {
    		return false;
    	}
		return true;
	}

}
