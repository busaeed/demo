package com.alt6wer.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.alt6wer.demo.validator.IdenticalPasswords;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@IdenticalPasswords
public class ResetPassword implements Passwordable {
	
    @Size(min = 6, max = 60)
	private String password;
	
    @NotBlank
	private String confirmedPassword;
    
    private String resetPasswordToken;

}
