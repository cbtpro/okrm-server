package com.useful.person.core.services;

import java.util.List;

import com.useful.person.core.domain.Tag;

/**
 * 
 * @author peter
 *
 */
public interface TagService {

    /**
     * 根据title查询tag
     * 
     * @param search
     * @param nd
     * @param rows
     * @param page
     * @param sidx
     * @param sord
     * @return List<Tag>
     */
    List<Tag> findByTitle(String search, String nd, int rows, int page, String sidx, String sord);

}
