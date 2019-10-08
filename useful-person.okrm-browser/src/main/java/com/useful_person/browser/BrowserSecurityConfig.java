package com.useful_person.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.useful_person.browser.authentication.OkrmAuthenticationFailureHandler;
import com.useful_person.browser.authentication.OkrmAuthenticationSuccessHandler;
import com.useful_person.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.useful_person.core.properties.SecurityConstants;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.redis.impl.SmsCodeRedisOperation;
import com.useful_person.core.validator.code.ValidatorCodeFilter;
import com.useful_person.core.validator.code.sms.SmsCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private SmsCodeRedisOperation smsCodeRedisOperation;

	@Autowired
	OkrmAuthenticationSuccessHandler okrmAuthenticationSuccessHandler;

	@Autowired
	OkrmAuthenticationFailureHandler okrmAuthenticationFailureHandler;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
//		jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		ValidatorCodeFilter validatorCodeFilter = new ValidatorCodeFilter();
		validatorCodeFilter.setAuthenticationFailureHandler(okrmAuthenticationFailureHandler);
		validatorCodeFilter.setSecurityProperties(securityProperties);
		validatorCodeFilter.afterPropertiesSet();

		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		smsCodeFilter.setAuthenticationFailureHandler(okrmAuthenticationFailureHandler);
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.setSmsCodeRedisOperation(smsCodeRedisOperation);
		smsCodeFilter.afterPropertiesSet();

		http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(validatorCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
				.failureUrl(SecurityConstants.DEFAULT_UNAUTHENTICATION_FAILURE_URL)
				.successHandler(okrmAuthenticationSuccessHandler).failureHandler(okrmAuthenticationFailureHandler).and()
				.rememberMe().tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService).and().authorizeRequests()
				.antMatchers("/", "/hello", SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_UNAUTHENTICATION_FAILURE_URL,
						securityProperties.getBrowser().getSigninPage(),
						SecurityConstants.DEFAULT_VALIDATOR_CODE_URL_PREFIX + "/*", "/favicon.ico")
				.permitAll().anyRequest().authenticated().and().csrf().disable()
				.apply(smsCodeAuthenticationSecurityConfig);
	}
}
