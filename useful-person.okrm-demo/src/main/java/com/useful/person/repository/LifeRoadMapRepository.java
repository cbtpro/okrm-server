/**
 * 
 */
package com.useful.person.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.LifeRoadMap;
import com.useful.person.domain.User;

/**
 * @author peter
 *
 */
public interface LifeRoadMapRepository extends JpaRepository<LifeRoadMap, String> {

	List<LifeRoadMap> findByUser(User user);
}
