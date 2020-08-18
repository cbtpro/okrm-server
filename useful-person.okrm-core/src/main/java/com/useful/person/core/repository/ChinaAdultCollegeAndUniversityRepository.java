package com.useful.person.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.ChinaAdultCollegeAndUniversity;

/**
 * 
 * @author peter
 *
 */
public interface ChinaAdultCollegeAndUniversityRepository extends JpaRepository<ChinaAdultCollegeAndUniversity, String> {

	/**
	 * 分页查询所有成人高校的信息
	 * 
	 * @param pageable
	 * @return Page<ChinaAdultCollegeAndUniversity> 带分页信息的成人高校列表
	 */
	Page<ChinaAdultCollegeAndUniversity> findAll(Pageable pageable);

	/**
	 * 根据名称分页查询成人高校信息
	 * @param name
	 * @param pageable
	 * @return Page<ChinaAdultCollegeAndUniversity> 带分页信息的成人高校列表
	 */
	Page<ChinaAdultCollegeAndUniversity> findByNameLike(String name, Pageable pageable);

}
