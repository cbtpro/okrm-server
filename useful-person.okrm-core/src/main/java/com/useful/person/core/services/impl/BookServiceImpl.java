/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Book;
import com.useful.person.core.exception.BookNotExistException;
import com.useful.person.core.repository.BookRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.BookService;

/**
 * @author peter
 *
 */
@Service("bookService")
public class BookServiceImpl implements BookService, BasicService<Book> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public Book findOne(String uuid) {
		return bookRepository.findById(uuid).orElseThrow(() -> new BookNotExistException(uuid));
	}

	@Override
	public Book saveOne(Book entity) {
		return bookRepository.save(entity);
	}

	@Override
	public Book findByUuid(String uuid) {
		return bookRepository.findById(uuid).orElseThrow(() -> new BookNotExistException(uuid));
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> saveAll(List<Book> entities) {
		return bookRepository.saveAll(entities);
	}

	@Override
	public void deleteOne(Book entity) {
		bookRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		bookRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		bookRepository.deleteAll();
	}

}
