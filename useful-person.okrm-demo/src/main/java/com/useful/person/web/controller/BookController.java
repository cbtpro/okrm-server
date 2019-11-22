/**
 * 
 */
package com.useful.person.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.domain.Book;
import com.useful.person.services.impl.BookServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookServiceImpl bookService;

	@ApiOperation(value = "查询所有书籍信息")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public Page<Book> queryAll(
			@PageableDefault(value = 15, sort = { "title" }, direction = Direction.DESC) Pageable pageable) {
		return bookService.findAll(pageable);
	}

	@ApiOperation(value = "根据uuid查询书籍")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Book queryBookByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
		return bookService.findOne(uuid);
	}

	@ApiOperation(value = "新增/更新一本书籍")
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.saveOne(book);
	}

	@ApiOperation(value = "根据uuid更新书籍")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Book updateBook(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Book entity) {
		return bookService.saveOne(entity);
	}

	@ApiOperation(value = "删除一本书")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void deleteBook(@PathVariable(name = "uuid", required = true) String uuid) {
		bookService.deleteByUuid(uuid);
	}
}
