/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.University;

/**
 * @author peter
 *
 */
public interface UniversityRepository extends JpaRepository<University, String> {

}
