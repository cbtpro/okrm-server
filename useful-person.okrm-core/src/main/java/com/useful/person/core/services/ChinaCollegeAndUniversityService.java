/**
 * 
 */
package com.useful.person.core.services;

import java.util.List;

import com.useful.person.core.vo.ChinaCollegeAndUniversityLocationVO;

/**
 * @author peter
 *
 */
public interface ChinaCollegeAndUniversityService {

	/**
	 * 查询所有大学的位置信息
	 * @return List<ChinaCollegeAndUniversityLocationVO> 所有大学的位置信息列表
	 */
	List<ChinaCollegeAndUniversityLocationVO> findAllLocation();

	/**
	 * 根据名称查询大学的位置信息
	 * @param name 大学名称
	 * @return List<ChinaCollegeAndUniversityLocationVO> 根据名称查询到的大学的位置信息列表
	 */
	List<ChinaCollegeAndUniversityLocationVO> findByNameLike(String name);
}
