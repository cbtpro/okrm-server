///**
// * 
// */
//package com.useful.person.browser;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author peter
// *
// */
//@Configurable
//public class BrowserCorsConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		WebMvcConfigurer.super.addCorsMappings(registry);
//		registry.addMapping("/**")
//			.allowedOrigins("*")
//			.allowedMethods("*")
//			.allowCredentials(true)
//			.allowedHeaders("Access-Control-Allow-Credentials", "true")
//			.maxAge(3600);
//	}
//}
