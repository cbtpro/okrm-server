/**
 * 
 */
package com.useful.person.core.redis.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.validator.mail.EmailCode;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author cbtpro
 *
 */
@Component
public class EmailCodeRedisOperation extends BasicRedisOperation {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	public void save(ServletWebRequest request, EmailCode emailCode, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(getRedisSessionKey(request), emailCode, timeout, unit);
	}

	public EmailCode get(ServletWebRequest request) {
		return (EmailCode) redisTemplate.opsForValue().get(getRedisSessionKey(request));
	}

	public void remove(ServletWebRequest request) {
		super.remove(getRedisSessionKey(request));
	}

	private String getRedisSessionKey(ServletWebRequest request) {
		String email = request.getParameter("email");
		return SecurityConstants.DEFAULT_SESSION_KEY_EMAIL_CODE + "_" + request.getSessionId() + (StringUtils.isNotBlank(email) ? "_" + email : "");
	}
}
