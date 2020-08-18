package com.useful.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 
 * @author peter
 *
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching(proxyTargetClass = true)
public class OkrmServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OkrmServerApplication.class, args);
	}

}
