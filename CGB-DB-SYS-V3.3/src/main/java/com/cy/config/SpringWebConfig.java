package com.cy.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cy.common.intercepter.TimeIntercepter;


import ch.qos.logback.core.joran.action.TimestampAction;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{
	@Override
	public  void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeIntercepter()).addPathPatterns("/user/doLogin");
		
	}
}
