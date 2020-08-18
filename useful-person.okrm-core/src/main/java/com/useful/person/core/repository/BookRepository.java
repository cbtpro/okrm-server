/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Book;

/**
 * @author peter
 *
 */
public interface BookRepository extends JpaRepository<Book, String> {

}
