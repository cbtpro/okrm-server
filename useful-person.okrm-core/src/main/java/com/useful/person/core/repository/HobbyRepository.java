/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Hobby;

/**
 * @author peter
 *
 */
public interface HobbyRepository extends JpaRepository<Hobby, String> {

}
