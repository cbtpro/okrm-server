package com.useful_person.core.validator.code.sms;

public interface SmsCodeSender {

	boolean send(String mobile, String code);
}
