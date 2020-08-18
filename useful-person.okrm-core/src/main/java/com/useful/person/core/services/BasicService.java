package com.useful.person.core.services;

import java.util.List;

/**
 * 
 * @author peter
 *
 * @param <T>
 */
public interface BasicService<T> {

	/**
	 * 添加对象
	 * @param entity
	 * @return T
	 */
	T saveOne(T entity);

	/**
	 * 添加多个对象
	 * @param entities
	 * @return
	 */
	List<T> saveAll(List<T> entities);

	/**
	 * 根据uuid查询对象
	 * @param uuid
	 * @return T
	 * @throws Exception 
	 */
	T findByUuid(String uuid);

	/**
	 * 查询所有
	 * @return List<T>
	 */
	List<T> findAll();

	/**
	 * 删除对象
	 * @param entity
	 */
	void deleteOne(T entity);
	
	/**
	 * 根据uuid删除对象
	 * @param uuid
	 */
	void deleteByUuid(String uuid);

	/**
	 * 删除所有
	 */
	void deleteAll();
}
