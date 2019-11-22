/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Event;

/**
 * @author peter
 *
 */
public interface EventRepository extends JpaRepository<Event, String> {

}
