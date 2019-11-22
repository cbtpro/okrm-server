/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Hobby;

/**
 * @author peter
 *
 */
public interface HobbyRepository extends JpaRepository<Hobby, String> {

}
