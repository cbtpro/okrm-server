/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.ChinaCollegeAndUniversity;

/**
 * @author peter
 *
 */
public interface ChinaCollegeAndUniversityRepository extends JpaRepository<ChinaCollegeAndUniversity, String> {

	/**
	 * 分页查询所有高校信息列表
	 * 
	 * @param pageable
	 * @return Page<ChinaCollegeAndUniversity> 带分页信息的高校信息列表
	 */
	Page<ChinaCollegeAndUniversity> findAll(Pageable pageable);

	/**
	 * 根据名称分页查询大学信息列表
	 * @param name 高校名称
	 * @param pageable 分页对象
	 * @return Page<ChinaCollegeAndUniversity> 带分页信息的高校信息列表
	 */
	Page<ChinaCollegeAndUniversity> findByNameLike(String name, Pageable pageable);
}
