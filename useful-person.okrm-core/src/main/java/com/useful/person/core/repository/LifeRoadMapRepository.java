/**
 * 
 */
package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.LifeRoadMap;
import com.useful.person.core.domain.UserInfo;

/**
 * @author peter
 *
 */
public interface LifeRoadMapRepository extends JpaRepository<LifeRoadMap, String> {

	/**
	 * 根据用户查询路线图
	 * @param userInfo
	 * @return
	 */
	List<LifeRoadMap> findByUser(UserInfo userInfo);
}