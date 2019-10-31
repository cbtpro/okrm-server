package com.useful.person.core.redis;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author peter
 *
 */
public interface RedisRepository {

	/**
	 * 根据key持久化对象到redis
	 * @param key
	 * @param object
	 * @param timeout
	 * @param unit
	 */
	void save(String key, Object object, long timeout, TimeUnit unit);

	/**
	 * 根据key从redis中获取对象
	 * @param key
	 * @return 存储的对象
	 */
	Object get(String key);

	/**
	 * 根据key从redis中移除对象
	 * @param key
	 */
	void remove(String key);

}
