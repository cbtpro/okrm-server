/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Subject;

/**
 * @author peter
 *
 */
public interface SubjectRepository extends JpaRepository<Subject, String> {

}
