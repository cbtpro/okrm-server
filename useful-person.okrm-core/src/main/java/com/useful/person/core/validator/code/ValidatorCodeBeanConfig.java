package com.useful.person.core.validator.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.validator.code.captcha.ImageCodeGenerator;
import com.useful.person.core.validator.code.sms.SmsCodeSender;
import com.useful.person.core.validator.code.sms.alidayu.DefaultSmsCodeSender;

/**
 * 
 * @author peter
 *
 */
@Configuration
public class ValidatorCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidatorCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}

	/**
	 * 使用@ConditionalOnMissingBean(SmsCodeSender.class)也可以
	 */
	@Bean
	@ConditionalOnMissingBean(name = "smsCodeSender")
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
