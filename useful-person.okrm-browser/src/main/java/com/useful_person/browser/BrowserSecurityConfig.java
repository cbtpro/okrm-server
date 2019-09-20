package com.useful_person.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		String basicSigninPage = "/basic-signin.html";
		http.formLogin().loginPage(basicSigninPage).loginProcessingUrl("/authentication/form").and().authorizeRequests()
				.antMatchers(basicSigninPage).permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}
}
