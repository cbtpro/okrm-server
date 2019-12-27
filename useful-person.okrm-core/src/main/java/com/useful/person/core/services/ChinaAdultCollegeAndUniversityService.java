/**
 * 
 */
package com.useful.person.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.useful.person.core.domain.ChinaAdultCollegeAndUniversity;

/**
 * @author peter
 *
 */
public interface ChinaAdultCollegeAndUniversityService {

	Page<ChinaAdultCollegeAndUniversity> findAll(Pageable pageable);

	Page<ChinaAdultCollegeAndUniversity> findByNameLike(String name, Pageable pageable);
}
