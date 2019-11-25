/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Major;

/**
 * @author peter
 *
 */
public interface MajorRepository extends JpaRepository<Major, String> {

}
