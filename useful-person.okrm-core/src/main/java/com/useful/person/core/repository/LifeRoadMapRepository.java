/**
 * 
 */
package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.useful.person.core.domain.LifeRoadMap;
import com.useful.person.core.domain.UserInfo;

/**
 * @author peter
 *
 */
public interface LifeRoadMapRepository extends JpaRepository<LifeRoadMap, String> {

	/**
	 * 根据用户查询路线图
	 * 
	 * @param userInfo
	 * @return
	 */
	@Query("select new com.useful.person.core.domain.LifeRoadMap(lrm.uuid, lrm.sex, lrm.age, lrm.event, lrm.description, lrm.times, lrm.updateTime, lrm.createTime) from LifeRoadMap lrm where lrm.user = ?1")
//	@Query(value = "select lrm.uuid, lrm.sex, lrm.age, lrm.event, lrm.description, lrm.times, lrm.updateTime, lrm.createTime from t_life_road_map lrm where lrm.user_uuid = ?1", nativeQuery = true)
	List<LifeRoadMap> findByUser(UserInfo userInfo);
}
