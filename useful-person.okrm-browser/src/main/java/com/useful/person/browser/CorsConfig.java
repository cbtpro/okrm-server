/**
 * 
 */
package com.useful.person.browser;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author peter
 *
 */
@Configurable
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**")
			.allowedHeaders("*")
			.allowedMethods("*")
			.allowedOrigins("*")
			.allowCredentials(true);
	}
}
