/**
 * 
 */
package com.useful.person.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.useful.person.core.domain.Book;

/**
 * @author peter
 *
 */
public interface BookService {

    /**
     * 查询所有书籍并分页
     * 
     * @param pageable
     * @return
     */
    Page<Book> findAll(Pageable pageable);

    /**
     * 根据uuid查询书籍
     * 
     * @param uuid
     * @return
     */
    Book findOne(String uuid);

    /**
     * 保存/更新书籍
     * 
     * @param entity
     * @return
     */
    Book saveOne(Book entity);

}
