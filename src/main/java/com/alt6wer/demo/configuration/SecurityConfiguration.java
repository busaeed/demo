package com.alt6wer.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alt6wer.demo.authentication.UserPrincipalService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserPrincipalService userPrincipalService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(userPrincipalService)
        	.passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /*http.authorizeRequests()
           //.antMatchers("/users/all").authenticated()
           //.antMatchers("/users/**").hasRole("admin")
           //.antMatchers("/users/all").hasAuthority("ADD_NEW_USER")
           .antMatchers("/").hasRole("USER")
           .anyRequest().permitAll()
           .and()
           .httpBasic();*/
        /*http
            .authorizeRequests()
                .antMatchers("/").hasRole("USER")
                .anyRequest().authenticated()
            .and()
            .httpBasic();*/
    	
    	http
    		.sessionManagement()
    			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    	
        http
            .authorizeRequests()
            	.antMatchers("/verifyEmail").permitAll()
            	.antMatchers("/register").anonymous()
            	.antMatchers("/forget-password").anonymous()
            	.antMatchers("/reset-password").anonymous()
                .anyRequest().authenticated()//permitAll()//anonymous()
                .and()
                .formLogin().loginPage("/login").usernameParameter("email").permitAll()
                .and()
                .logout().permitAll()
                .and()
                //you don't have to use the second validity method because by default it's 2 weeks or specify it in seconds.
                //if you face a problem also add this .userDetailsService(myUserDetailsService)
                .rememberMe().tokenValiditySeconds(30*24*60*60)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

}
