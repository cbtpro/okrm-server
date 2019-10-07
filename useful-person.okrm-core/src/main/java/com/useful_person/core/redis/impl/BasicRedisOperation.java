package com.useful_person.core.redis.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.useful_person.core.redis.RedisRepository;

@Component
public class BasicRedisOperation implements RedisRepository {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void save(String key, Object object) {
		redisTemplate.opsForValue().set(key, object, 30, TimeUnit.MINUTES);
	}

	@Override
	public Object get(String key) {
		Object value = redisTemplate.opsForValue().get(key);
		if (value == null) {
			return null;
		}
		return value;
	}

	@Override
	public void remove(String key) {
		redisTemplate.delete(key);
	}

}
