/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Country;

/**
 * @author cbtpro
 *
 */
public interface CountryRepository extends JpaRepository<Country, String> {

}
