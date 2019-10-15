package com.useful_person.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.useful_person.browser.authentication.OkrmAuthenticationFailureHandler;
import com.useful_person.browser.authentication.OkrmAuthenticationSuccessHandler;
import com.useful_person.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.useful_person.core.properties.BrowserProperties;
import com.useful_person.core.properties.SecurityConstants;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.redis.impl.SmsCodeRedisOperation;
import com.useful_person.core.validator.code.ValidatorCodeFilter;
import com.useful_person.core.validator.code.sms.SmsCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

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

		BrowserProperties browserProperties = securityProperties.getBrowser();
		http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(validatorCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
				.failureUrl(SecurityConstants.DEFAULT_UNAUTHENTICATION_FAILURE_URL)
				.successHandler(okrmAuthenticationSuccessHandler).failureHandler(okrmAuthenticationFailureHandler)
				// 记住我功能
				.and().rememberMe().tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				// 不需要登录的接口
				.and().authorizeRequests()
				.antMatchers("/", "/hello", SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_UNAUTHENTICATION_FAILURE_URL, SecurityConstants.DEFAULT_SIGN_UP_URL,
						browserProperties.getSigninPage(), browserProperties.getSignupPage(),
						SecurityConstants.DEFAULT_VALIDATOR_CODE_URL_PREFIX + "/*",
						SecurityConstants.DEFAULT_ACTIVATE_URL_PREFIX + "/*",
						SecurityConstants.DEFAULT_ACTIVATE_URL_PREFIX + "/*/*", "/auth/qq/*", "/favicon.ico")
				.permitAll().anyRequest().authenticated()
				// 禁用csrf
				.and().csrf().disable()
				// 应用短信验证配置
				.apply(smsCodeAuthenticationSecurityConfig);
	}
}
