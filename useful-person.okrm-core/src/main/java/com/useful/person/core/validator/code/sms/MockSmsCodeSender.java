package com.useful.person.core.validator.code.sms;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author peter
 *
 */
@Log4j2
public class MockSmsCodeSender implements SmsCodeSender {

	@Override
	public boolean send(String mobile, String code) {
		log.info("模拟向手机：" + mobile + " 发送验证码：" + code);
		return true;
	}

}
