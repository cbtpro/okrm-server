/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Suggest;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.SuggestRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.SuggestService;

/**
 * @author peter
 *
 */
@Service(value = "suggestService")
public class SuggestServiceImpl implements SuggestService, BasicService<Suggest> {

	@Autowired
	private SuggestRepository suggestRepository;

	@Override
	public Suggest saveOne(Suggest entity) {
		return suggestRepository.save(entity);
	}

	@Deprecated
	@Override
	public List<Suggest> saveAll(List<Suggest> entities) {
		return suggestRepository.saveAll(entities);
	}

	@Override
	public Suggest findByUuid(String uuid) {
		return suggestRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(uuid, "suggest not found"));
	}

	@Override
	public List<Suggest> findAll() {
		return suggestRepository.findAll();
	}

	@Deprecated
	@Override
	public void deleteOne(Suggest entity) {
		suggestRepository.delete(entity);
	}

	@Deprecated
	@Override
	public void deleteByUuid(String uuid) {
		suggestRepository.deleteById(uuid);
	}

	@Deprecated
	@Override
	public void deleteAll() {
		suggestRepository.deleteAll();
	}
	
}
