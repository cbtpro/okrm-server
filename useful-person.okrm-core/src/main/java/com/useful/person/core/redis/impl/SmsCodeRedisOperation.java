package com.useful.person.core.redis.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.validator.code.sms.SmsCode;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * 
 * @author peter
 *
 */
@Component
public class SmsCodeRedisOperation extends BasicRedisOperation {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	public void save(ServletWebRequest request, SmsCode smsCode, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(getRedisSessionKey(request), smsCode, timeout, unit);
	}
	
	public SmsCode get(ServletWebRequest request) {
		return (SmsCode) redisTemplate.opsForValue().get(getRedisSessionKey(request));
	}

	public void remove(ServletWebRequest request) {
		super.remove(getRedisSessionKey(request));
	}

	private String getRedisSessionKey(ServletWebRequest request) {
		String mobile = request.getParameter("mobile");
		return SecurityConstants.DEFAULT_SESSION_KEY_SMS_CODE + "_" + request.getSessionId() + (StringUtils.isNotBlank(mobile) ? "_" + mobile : "");
	}
}
