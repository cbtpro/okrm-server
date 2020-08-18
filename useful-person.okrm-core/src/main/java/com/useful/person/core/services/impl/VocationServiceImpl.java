/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Vocation;
import com.useful.person.core.repository.VocationRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.VocationService;

/**
 * @author peter
 *
 */
@Service("vocationService")
public class VocationServiceImpl implements VocationService, BasicService<Vocation> {

	@Autowired
	private VocationRepository vocationRepository;
	
	@Override
	public Vocation findByUuid(String uuid) {
		return vocationRepository.findById(uuid).orElse(null);
	}

	@Override
	public Vocation saveOne(Vocation entity) {
		return vocationRepository.save(entity);
	}

	@Override
	public List<Vocation> saveAll(List<Vocation> entities) {
		return vocationRepository.saveAll(entities);
	}

	@Override
	public void deleteOne(Vocation entity) {
		vocationRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		vocationRepository.deleteById(uuid);
	}

	@Override
	public List<Vocation> findAll() {
		return vocationRepository.findAll();
	}

	@Override
	public void deleteAll() {
		vocationRepository.deleteAll();
	}

}
