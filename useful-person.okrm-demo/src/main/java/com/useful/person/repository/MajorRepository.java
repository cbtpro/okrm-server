/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Major;

/**
 * @author peter
 *
 */
public interface MajorRepository extends JpaRepository<Major, String> {

}
