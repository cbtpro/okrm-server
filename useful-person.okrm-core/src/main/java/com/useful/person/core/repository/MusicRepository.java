/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Music;

/**
 * @author peter
 *
 */
public interface MusicRepository extends JpaRepository<Music, String> {

}
