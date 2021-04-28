package com.alt6wer.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.alt6wer.demo.model.Passwordable;

public class IdenticalPasswordsValidator implements ConstraintValidator<IdenticalPasswords, Passwordable> {
	
	private String field;
	private String message;
	
	@Override
	public void initialize(IdenticalPasswords constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(Passwordable passwordForm, ConstraintValidatorContext context) {
		if (!passwordForm.getPassword().equals(passwordForm.getConfirmedPassword())) {
			context.disableDefaultConstraintViolation();
		      context.buildConstraintViolationWithTemplate(message)
		          .addPropertyNode(field)
		          .addConstraintViolation();
		      return false;
		}
		return true;
	}

}
