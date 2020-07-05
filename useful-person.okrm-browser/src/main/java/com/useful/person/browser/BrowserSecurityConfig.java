package com.useful.person.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.useful.person.browser.authentication.OkrmAuthenticationFailureHandler;
import com.useful.person.browser.authentication.OkrmAuthenticationSuccessHandler;
import com.useful.person.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.useful.person.core.properties.BrowserProperties;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.redis.impl.EmailCodeRedisOperation;
import com.useful.person.core.redis.impl.SmsCodeRedisOperation;
import com.useful.person.core.validator.code.ValidatorCodeFilter;
import com.useful.person.core.validator.code.sms.SmsCodeFilter;
import com.useful.person.core.validator.mail.EmailCodeFilter;

/**
 * 
 * @author peter
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private SmsCodeRedisOperation smsCodeRedisOperation;

	@Autowired
	private EmailCodeRedisOperation emailCodeRedisOperation;

	@Autowired
	OkrmAuthenticationSuccessHandler okrmAuthenticationSuccessHandler;

	@Autowired
	OkrmAuthenticationFailureHandler okrmAuthenticationFailureHandler;

//	@Autowired
//	OkrmLogoutSuccess okrmLogoutSuccess;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
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

		EmailCodeFilter emailCodeFilter = new EmailCodeFilter();
		emailCodeFilter.setAuthenticationFailureHandler(okrmAuthenticationFailureHandler);
		emailCodeFilter.setSecurityProperties(securityProperties);
		emailCodeFilter.setEmailCodeRedisOperation(emailCodeRedisOperation);
		emailCodeFilter.afterPropertiesSet();

		BrowserProperties browserProperties = securityProperties.getBrowser();
		String signoutPage = browserProperties.getSignoutPage();
		http.addFilterBefore(emailCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(validatorCodeFilter, EmailCodeFilter.class).formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
				.failureUrl(SecurityConstants.DEFAULT_UNAUTHENTICATION_FAILURE_URL)
				.successHandler(okrmAuthenticationSuccessHandler).failureHandler(okrmAuthenticationFailureHandler)
				// 登出功能
				.and().logout().permitAll()
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
				// 记住我功能
				.and().rememberMe().tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(browserProperties.getRememberMeSeconds())
//				.userDetailsService(userDetailsService)
				// 不需要登录的接口
				.and().authorizeRequests()
				.antMatchers("/", "/suggest", "/error",
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_UNAUTHENTICATION_FAILURE_URL,
						SecurityConstants.DEFAULT_SIGN_UP_URL,
						SecurityConstants.DEFAULT_SIGN_UP_MOBILE_URL,
						SecurityConstants.DEFAULT_SIGN_UP_EMAIL_URL,
						browserProperties.getSigninPage(),
						browserProperties.getSignupPage(),
						signoutPage,
						SecurityConstants.DEFAULT_VALIDATOR_CODE_URL_PREFIX + "/*", // 验证码
						SecurityConstants.DEFAULT_ACTIVATE_URL_PREFIX + "/*", // 激活
						SecurityConstants.DEFAULT_ACTIVATE_URL_PREFIX + "/*/*", "/auth/*", "/favicon.ico")
				.permitAll().anyRequest().authenticated()
				// 禁用csrf
				.and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.and()
				// 应用短信验证配置
				.apply(smsCodeAuthenticationSecurityConfig);
	}
}
