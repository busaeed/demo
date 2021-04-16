package com.alt6wer.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdenticalPasswordsValidator.class)
public @interface IdenticalPasswords {
	String message() default "Your password and confirmed password are not identical";
	Class<?>[] groups() default {};
	public abstract Class<? extends Payload>[] payload() default {};
	String field() default "confirmedPassword";
}
