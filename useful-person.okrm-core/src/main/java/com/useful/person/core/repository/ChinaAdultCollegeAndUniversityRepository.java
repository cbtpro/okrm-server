package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.useful.person.core.domain.ChinaAdultCollegeAndUniversity;
import com.useful.person.core.vo.ChinaAdultCollegeAndUniversityLocationVO;

/**
 * 
 * @author peter
 *
 */
public interface ChinaAdultCollegeAndUniversityRepository extends JpaRepository<ChinaAdultCollegeAndUniversity, String> {

	/**
	 * 查询所有成人高校的位置信息
	 * 
	 * @param userInfo
	 * @return List<ChinaCollegeAndUniversityLocationVO>
	 */
	@Query("select new com.useful.person.core.vo.ChinaAdultCollegeAndUniversityLocationVO(uuid, number, name, longitude, latitude) from ChinaAdultCollegeAndUniversity")
	List<ChinaAdultCollegeAndUniversityLocationVO> findAllLocation();

}
