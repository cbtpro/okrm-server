package com.useful_person.core.validator.code.sms;

public interface SmsCodeSender {

	void send(String mobile, String code);
}
