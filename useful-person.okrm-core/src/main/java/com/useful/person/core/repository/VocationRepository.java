/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Vocation;

/**
 * @author peter
 *
 */
public interface VocationRepository extends JpaRepository<Vocation, String> {

}
