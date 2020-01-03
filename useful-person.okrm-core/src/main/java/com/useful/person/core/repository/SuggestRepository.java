/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Suggest;

/**
 * @author peter
 *
 */
public interface SuggestRepository extends JpaRepository<Suggest, String> {

}
