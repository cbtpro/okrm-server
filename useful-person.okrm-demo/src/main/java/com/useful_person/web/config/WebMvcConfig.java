package com.useful_person.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.useful_person.interceptor.StatisticalInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

	@Autowired
	private StatisticalInterceptor statisticalInterceptor;
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(statisticalInterceptor);
	}
}
