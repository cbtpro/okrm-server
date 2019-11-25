package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Tag;
import com.useful.person.core.repository.TagRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.TagService;

/**
 * 
 * @author peter
 *
 */
@Service("TagService")
public class TagServiceImpl implements TagService, BasicService<Tag> {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public Tag findByUuid(String uuid) {
		return tagRepository.findById(uuid).orElse(null);
	}

	@Override
	public List<Tag> findByTitle(String search, String nd, int rows, int page, String sidx, String sord) {
		Sort sort = new Sort("DESC".equalsIgnoreCase(sord) ? Direction.DESC : Direction.ASC, sidx);
		return tagRepository.findByTitle(search, sort);
	}

	@Override
	public Tag saveOne(Tag entity) {
		return tagRepository.save(entity);
	}

	@Override
	public List<Tag> saveAll(List<Tag> entities) {
		return tagRepository.saveAll(entities);
	}

	@Override
	public void deleteOne(Tag entity) {
		tagRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		tagRepository.deleteById(uuid);
	}

	@Override
	public List<Tag> findAll() {
		// TODO 只能查询当前登录用户下的列表
		return tagRepository.findAll();
	}

	@Override
	public void deleteAll() {
		tagRepository.deleteAll();
	}

}
