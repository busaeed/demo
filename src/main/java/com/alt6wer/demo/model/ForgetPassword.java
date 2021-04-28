package com.alt6wer.demo.model;

import com.alt6wer.demo.validator.ValidEmail;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetPassword {
	
	@ValidEmail
	private String email;

}
