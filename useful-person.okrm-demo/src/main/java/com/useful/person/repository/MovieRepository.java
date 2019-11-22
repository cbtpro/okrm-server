/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Movie;

/**
 * @author peter
 *
 */
public interface MovieRepository extends JpaRepository<Movie, String> {

}
