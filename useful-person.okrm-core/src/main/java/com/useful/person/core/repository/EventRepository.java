/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Event;

/**
 * @author peter
 *
 */
public interface EventRepository extends JpaRepository<Event, String> {

}
