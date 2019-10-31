package com.useful.person.services;

import java.util.List;

/**
 * 
 * @author peter
 *
 * @param <T>
 */
public interface BasicService<T> {

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
	 * 添加对象
	 * @param entity
	 * @return T
	 */
	T addOne(T entity);

	/**
	 * 添加多个对象
	 * @param entities
	 * @return
	 */
	List<T> addAll(List<T> entities);

	/**
	 * 更新对象
	 * @param entity
	 * @return
	 */
	T updateOne(T entity);

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
}
