/**
 * 
 */
package com.useful.person.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.useful.person.domain.Book;

/**
 * @author peter
 *
 */
public interface BookService {

	Page<Book> findAll(Pageable pageable);

	Book findOne(String uuid);

	Book saveOne(Book entity);

}
