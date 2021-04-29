package com.alt6wer.demo.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alt6wer.demo.interceptor.CurrentPageInterceptor;

@Configuration
public class WebConfiguration extends WebMvcAutoConfiguration implements WebMvcConfigurer {
	
	@Bean
	public CurrentPageInterceptor currentPageInterceptor() {
		return new CurrentPageInterceptor();
	}
	
	private static final List<String> STATIC_RESOURCE_LOCATIONS = Arrays.asList("/META-INF/resources/**", "/resources/**", "/static/**", "/public/**");

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//without pattern for global interceptor.
		//if a route is redirected then the first route interceptor doesn't get invoked.
		//.order(1) or any number other than 1
		//"/product" => only root, "/product/*" => only children, "/product/**" => root & children
		registry.addInterceptor(currentPageInterceptor()).addPathPatterns("/**").excludePathPatterns(STATIC_RESOURCE_LOCATIONS);//.addPathPatterns("/");
		//try the following next time .addPathPatterns("/**").excludePathPatterns("/public/**") or remove one star from all elements in array
		// https://stackoverflow.com/questions/53460730/spring-boot-2-0-intercepting-the-handlermethod-of-a-request
	}
	
	

}
