package com.alt6wer.demo.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.Role;
import com.alt6wer.demo.repository.MemberRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private RoleService roleService;
    
    public Member createMember(Member member) throws UnsupportedEncodingException, MessagingException {
        member.setHashedPassword(this.hashPassword(member.getPassword()));
        member.setRegisteredAt(this.getCurrentLocalDateTime());
        member.setVerificationCode(RandomString.make(64));
        //user.setLastActivity(currentDateTime.toEpochSecond(ZoneOffset.UTC));
        //member.setActive(true);
        if (member.getRole() == null) {
        	member.setRole(this.getDefaultRole());
        }
        return memberRepository.save(member);
    }
    
    private String hashPassword(final String rawPassword) {
    	return new BCryptPasswordEncoder().encode(rawPassword);
    }
    
    private LocalDateTime getCurrentLocalDateTime() {
    	return LocalDateTime.now(ZoneOffset.UTC);
    }
    
    private Role getDefaultRole() {
    	return roleService.findByRoleName("ROLE_USER");
    }
    
    public void sendVerificationEmail(Member member) throws UnsupportedEncodingException, MessagingException {
        String content = "Dear " + member.getUsername() + ",<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"" + "http://localhost:8080/verify?verificationCode=" + member.getVerificationCode() + "\" target=\"_self\">VERIFY</a></h3>"
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

    public Member findById(int memberId) {
        return memberRepository.findById(memberId);
    }
    
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    
    public Member findByVerificationCode(String verificationCode) {
    	return memberRepository.findByVerificationCode(verificationCode);
    }
    
    public Member verify(String verificationCode) {
    	Member member = memberRepository.findByVerificationCode(verificationCode);
    	if (member == null || member.isActive()) {
    		return null;
    	}
    	member.setVerificationCode(null);
    	member.setActive(true);
    	return memberRepository.save(member);
    }

}
