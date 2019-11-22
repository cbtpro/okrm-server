/**
 * 
 */
package com.useful.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.domain.Book;

/**
 * @author peter
 *
 */
public interface BookRepository extends JpaRepository<Book, String> {

}
