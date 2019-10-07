package com.useful_person.core.redis;

public interface RedisRepository {

	void save(String key, Object object);

	Object get(String key);

	void remove(String key);

}
