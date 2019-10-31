package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.useful.person.domain.Tag;
import com.useful.person.repository.TagRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.TagService;

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
	public Tag addOne(Tag entity) {
		return tagRepository.save(entity);
	}

	@Override
	public List<Tag> addAll(List<Tag> entities) {
		return tagRepository.saveAll(entities);
	}

	@Override
	public Tag updateOne(Tag entity) {
		return tagRepository.save(entity);
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
		return null;
	}

}
