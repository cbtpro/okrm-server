package com.useful.person.core.validator.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peter
 *
 */
@Slf4j
public class MockSmsCodeSender implements SmsCodeSender {

	@Override
	public boolean send(String mobile, String code) {
		log.info("模拟向手机：" + mobile + " 发送验证码：" + code);
		return true;
	}

}
