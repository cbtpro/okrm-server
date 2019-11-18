package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Schedule;

/**
 * 
 * @author peter
 *
 */
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

}
