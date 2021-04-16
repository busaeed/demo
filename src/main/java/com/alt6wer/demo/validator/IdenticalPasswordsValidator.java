package com.alt6wer.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.alt6wer.demo.model.Member;

public class IdenticalPasswordsValidator implements ConstraintValidator<IdenticalPasswords, Member> {
	
	private String field;
	private String message;
	
	@Override
	public void initialize(IdenticalPasswords constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Member member, ConstraintValidatorContext context) {
		if (!member.getPassword().equals(member.getConfirmedPassword())) {
			context.disableDefaultConstraintViolation();
		      context.buildConstraintViolationWithTemplate(message)
		          .addPropertyNode(field)
		          .addConstraintViolation();
		      return false;
		}
		return true;
	}

}
