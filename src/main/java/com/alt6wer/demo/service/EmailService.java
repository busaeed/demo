package com.alt6wer.demo.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Member;

@Service
public class EmailService {
	
    @Autowired
    private JavaMailSender mailSender;
	
	public void sendVerificationEmail(Member member) throws UnsupportedEncodingException, MessagingException {
        String content = "Dear " + member.getUsername() + ",<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"" + "http://localhost:8080/verifyEmail?verificationCode=" + member.getVerificationCode() + "\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("bosaeed2011@gmail.com", "SpringBootWebsite");
        helper.setTo(member.getEmail());
        helper.setSubject("Verification code for springboot app");
        helper.setText(content, true); //true to enable html (optional parameter)
        
        mailSender.send(message);
    }

	public void sendResetPasswordEmail(Member member) throws UnsupportedEncodingException, MessagingException {
		if (member == null) {
			return;
		}
		String content = "Dear " + member.getUsername() + ",<br>"
                + "Please click the link below to reset your password:<br>"
                + "<h3><a href=\"" + "http://localhost:8080/reset-password?resetPasswordToken=" + member.getResetPasswordToken() + "\" target=\"_self\">RESET PASSWORD</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("bosaeed2011@gmail.com", "SpringBootWebsite");
        helper.setTo(member.getEmail());
        helper.setSubject("reset password for springboot app");
        helper.setText(content, true); //true to enable html (optional parameter)
        
        mailSender.send(message);
	}

}
