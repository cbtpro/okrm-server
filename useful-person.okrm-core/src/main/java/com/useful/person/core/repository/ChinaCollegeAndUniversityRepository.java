/**
 * 
 */
package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.useful.person.core.domain.ChinaCollegeAndUniversity;
import com.useful.person.core.vo.ChinaCollegeAndUniversityLocationVO;

/**
 * @author peter
 *
 */
public interface ChinaCollegeAndUniversityRepository extends JpaRepository<ChinaCollegeAndUniversity, String> {

	/**
	 * 查询所有高校的位置信息列表
	 * 
	 * @param userInfo
	 * @return List<ChinaCollegeAndUniversityLocationVO> 高校信息位置列表
	 */
	@Query("select new com.useful.person.core.vo.ChinaCollegeAndUniversityLocationVO(uuid, number, name, province, longitude, latitude) from ChinaCollegeAndUniversity")
	List<ChinaCollegeAndUniversityLocationVO> findAllLocation();

	/**
	 * 根据名称查询大学的位置信息列表
	 * @param name 高校名称
	 * @return List<ChinaCollegeAndUniversityLocationVO> 高校信息位置列表
	 */
	@Query("select new com.useful.person.core.vo.ChinaCollegeAndUniversityLocationVO(uuid, number, name, province, longitude, latitude) from ChinaCollegeAndUniversity where name like :name")
	List<ChinaCollegeAndUniversityLocationVO> findByNameLike(String name);
}
