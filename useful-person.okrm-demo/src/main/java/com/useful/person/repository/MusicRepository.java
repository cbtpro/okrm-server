/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Music;

/**
 * @author peter
 *
 */
public interface MusicRepository extends JpaRepository<Music, String> {

}
