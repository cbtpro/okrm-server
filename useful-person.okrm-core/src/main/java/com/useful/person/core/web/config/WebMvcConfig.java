package com.useful.person.core.web.config;

//package com.useful_person.web.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import com.useful_person.interceptor.StatisticalInterceptor;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//
//	@Autowired
//	private StatisticalInterceptor statisticalInterceptor;
//
//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(statisticalInterceptor);
//	}
//
////	@Override
////	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
////		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
////		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
////		registry.addResourceHandler("basic-signin.html").addResourceLocations("classpath:/resources/");
////	}
////	@Override
////	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
////		configurer.setDefaultTimeout(timeout) // set up Async timeout
////		configurer.setTaskExecutor(taskExecutor) // 设置可重用的线程池
////	}
//}
