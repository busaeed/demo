package com.alt6wer.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("gant").password(encodePassword().encode("123456")).roles("admin").authorities("ADD_NEW_USER")
            .and()
            .withUser("shb27").password(encodePassword().encode("123456")).roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
           //.antMatchers("/users/all").authenticated()
           //.antMatchers("/users/**").hasRole("admin")
           //.antMatchers("/users/all").hasAuthority("ADD_NEW_USER")
           .anyRequest().permitAll()
           .and()
           .httpBasic();
    }
    
    @Bean
    PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

}
