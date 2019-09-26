package com.useful_person.core.validator.code.sms;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		log.info("向手机：" + mobile + " 发送验证码：" + code);
	}

}
