package com.useful_person.core.redis;

import java.util.concurrent.TimeUnit;

public interface RedisRepository {

	void save(String key, Object object, long timeout, TimeUnit unit);

	Object get(String key);

	void remove(String key);

}
