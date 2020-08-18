/**
 * 
 */
package com.useful.person.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.useful.person.core.domain.ChinaAdultCollegeAndUniversity;

/**
 * @author peter
 *
 */
public interface ChinaAdultCollegeAndUniversityService {


	/**
	 * 分页查询所有中国成人高校信息列表
	 * @param pageable 分页对象
	 * @return Page<ChinaAdultCollegeAndUniversity> 分页查询到的中国成人高校信息列表
	 */
	Page<ChinaAdultCollegeAndUniversity> findAll(Pageable pageable);

	/**
	 * 根据名称分页查询中国成人高校信息列表
	 * @param name 名称
	 * @param pageable 分页对象
	 * @return Page<ChinaAdultCollegeAndUniversity> 根据名称分页查询到的中国成人高校信息列表
	 */
	Page<ChinaAdultCollegeAndUniversity> findByNameLike(String name, Pageable pageable);
}
