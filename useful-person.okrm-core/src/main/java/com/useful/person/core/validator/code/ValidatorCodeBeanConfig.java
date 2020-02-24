package com.useful.person.core.validator.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.validator.code.captcha.ImageCodeGenerator;
import com.useful.person.core.validator.code.sms.MockSmsCodeSender;
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
	@Bean("smsCodeSender")
//	@Profile({"prod", "test"})
	@ConditionalOnMissingBean(name = "smsCodeSender")
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

	/**
	 * 测试环境使用模拟发送短信
	 * @return
	 */
//	@Bean("smsCodeSender")
//	@Profile({"dev"})
//	@ConditionalOnMissingBean(name = "smsCodeSender")
//	public SmsCodeSender mockSmsCodeSender() {
//		return new MockSmsCodeSender();
//	}
}
