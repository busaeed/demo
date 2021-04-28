package com.alt6wer.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alt6wer.demo.interceptor.CurrentPageInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Bean
	public CurrentPageInterceptor currentPageInterceptor() {
		return new CurrentPageInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//without pattern for global interceptor.
		//if a route is redirected then the first route interceptor doesn't get invoked.
		//.order(1) or any number other than 1
		//"/product" => only root, "/product/*" => only children, "/product/**" => root & children
		registry.addInterceptor(currentPageInterceptor()).addPathPatterns("/**").excludePathPatterns("/public/**");//.addPathPatterns("/");
		//try the following next time .addPathPatterns("/**").exludePathPatterns("/")
		// or this .addPathPatterns("/**").excludePathPatterns("public/**")
		// https://stackoverflow.com/questions/53460730/spring-boot-2-0-intercepting-the-handlermethod-of-a-request
	}
	
	

}
