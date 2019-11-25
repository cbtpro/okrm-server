/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.University;

/**
 * @author peter
 *
 */
public interface UniversityRepository extends JpaRepository<University, String> {

}
