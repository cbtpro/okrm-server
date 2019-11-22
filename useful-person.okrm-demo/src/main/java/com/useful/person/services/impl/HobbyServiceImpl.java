/**
 * 
 */
package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.useful.person.domain.Hobby;
import com.useful.person.exception.HobbyNotExistException;
import com.useful.person.repository.HobbyRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.HobbyService;

/**
 * @author peter
 *
 */
public class HobbyServiceImpl implements HobbyService, BasicService<Hobby> {

	@Autowired
	private HobbyRepository hobbyRepository;

	@Override
	public Hobby saveOne(Hobby entity) {
		return hobbyRepository.save(entity);
	}

	@Override
	public List<Hobby> saveAll(List<Hobby> entities) {
		return hobbyRepository.saveAll(entities);
	}

	@Override
	public Hobby findByUuid(String uuid) {
		return hobbyRepository.findById(uuid).orElseThrow(() -> new HobbyNotExistException(uuid));
	}

	@Override
	public List<Hobby> findAll() {
		return hobbyRepository.findAll();
	}

	@Override
	public void deleteOne(Hobby entity) {
		hobbyRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		hobbyRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		hobbyRepository.deleteAll();		
	}

}
