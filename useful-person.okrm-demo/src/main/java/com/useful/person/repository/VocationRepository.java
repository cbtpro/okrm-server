/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Vocation;

/**
 * @author peter
 *
 */
public interface VocationRepository extends JpaRepository<Vocation, String> {

}
