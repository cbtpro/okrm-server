package com.useful_person.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
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

	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//		configurer.setDefaultTimeout(timeout) // set up Async timeout
//		configurer.setTaskExecutor(taskExecutor) // 设置可重用的线程池
	}
}
