package com.useful_person.core.redis.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.useful_person.core.properties.SecurityConstants;
import com.useful_person.core.properties.SecurityProperties;
import com.useful_person.core.validator.code.sms.SmsCode;

@Component
public class SmsCodeRedisOperation extends BasicRedisOperation {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	public void save(ServletWebRequest request, SmsCode smsCode) {
		redisTemplate.opsForValue().set(getRedisSessionKey(request), smsCode, securityProperties.getCode().getSms().getExpireIn(), TimeUnit.SECONDS);
	}
	
	public SmsCode get(ServletWebRequest request) {
		return (SmsCode) redisTemplate.opsForValue().get(getRedisSessionKey(request));
	}

	public void remove(ServletWebRequest request) {
		super.remove(getRedisSessionKey(request));
	}

	private String getRedisSessionKey(ServletWebRequest request) {
		return SecurityConstants.DEFAULT_SESSION_KEY_SMS_CODE + "_" + request.getSessionId();
	}
}
