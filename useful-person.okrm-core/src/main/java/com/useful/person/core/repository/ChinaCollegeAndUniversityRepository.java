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
	 * 根据用户查询路线图
	 * 
	 * @param userInfo
	 * @return
	 */
	@Query("select new com.useful.person.core.vo.ChinaCollegeAndUniversityLocationVO(uuid, number, name, longitude, latitude) from ChinaCollegeAndUniversity")

	List<ChinaCollegeAndUniversityLocationVO> findAllLocation();
}
