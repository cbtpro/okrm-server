/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Movie;

/**
 * @author peter
 *
 */
public interface MovieRepository extends JpaRepository<Movie, String> {

}
