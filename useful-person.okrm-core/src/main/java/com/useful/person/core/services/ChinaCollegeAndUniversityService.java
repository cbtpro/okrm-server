/**
 * 
 */
package com.useful.person.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.useful.person.core.domain.ChinaCollegeAndUniversity;

/**
 * @author peter
 *
 */
public interface ChinaCollegeAndUniversityService {

	/**
	 * 分页查询所有大学的位置信息
	 * @return List<ChinaCollegeAndUniversityLocationVO> 分页查询到的大学的位置信息列表
	 */
	Page<ChinaCollegeAndUniversity> findAll(Pageable pageable);

	/**
	 * 根据名称分页查询大学的位置信息
	 * @param name 大学名称
	 * @return List<ChinaCollegeAndUniversityLocationVO> 根据名称分页查询到的大学的位置信息列表
	 */
	Page<ChinaCollegeAndUniversity> findByNameLike(String name, Pageable pageable);
}
