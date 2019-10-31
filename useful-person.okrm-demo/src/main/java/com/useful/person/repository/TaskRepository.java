package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Task;

/**
 * 
 * @author peter
 *
 */
public interface TaskRepository extends JpaRepository<Task, String> {

}
