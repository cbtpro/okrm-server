package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.Tag;

/**
 * 
 * @author peter
 *
 */
public interface TagRepository extends JpaRepository<Tag, String> {

    /**
     * 根据标题查询tag并返回排序结果
     * 
     * @param title
     * @param sort
     * @return
     */
    List<Tag> findByTitle(String title, Sort sort);

}
