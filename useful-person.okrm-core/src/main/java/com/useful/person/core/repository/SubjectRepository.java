/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Subject;

/**
 * @author peter
 *
 */
public interface SubjectRepository extends JpaRepository<Subject, String> {

}
