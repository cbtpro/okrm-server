package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Schedule;

/**
 * 
 * @author peter
 *
 */
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

}
