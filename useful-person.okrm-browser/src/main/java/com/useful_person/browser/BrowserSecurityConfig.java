package com.useful_person.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.useful_person.browser.authentication.OkrmAuthenticationFailureHandler;
import com.useful_person.browser.authentication.OkrmAuthenticationSuccessHandler;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.validator.code.ValidatorCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	OkrmAuthenticationSuccessHandler okrmAuthenticationSuccessHandler;
	@Autowired
	OkrmAuthenticationFailureHandler okrmAuthenticationFailureHandler;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		ValidatorCodeFilter validatorCodeFilter = new ValidatorCodeFilter();
		validatorCodeFilter.setAuthenticationFailureHandler(okrmAuthenticationFailureHandler);
		http.addFilterBefore(validatorCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin()
				.loginPage("/authentication/require").loginProcessingUrl("/authentication/form")
				.failureUrl("/authentication/failure").successHandler(okrmAuthenticationSuccessHandler)
				.failureHandler(okrmAuthenticationFailureHandler).and().authorizeRequests()
				.antMatchers("/", "/hello", "/authentication/require", "/authentication/failure",
						securityProperties.getBrowser().getSigninPage(), "/code/captcha.jpg", "/favicon.ico")
				.permitAll().anyRequest().authenticated().and().csrf().disable();
	}
}
