/**
 * 
 */
package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.domain.Vocation;
import com.useful.person.repository.VocationRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.VocationService;

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
	public Vocation addOne(Vocation entity) {
		return vocationRepository.save(entity);
	}

	@Override
	public List<Vocation> addAll(List<Vocation> entities) {
		return vocationRepository.saveAll(entities);
	}

	@Override
	public Vocation updateOne(Vocation entity) {
		return vocationRepository.save(entity);
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
		// TODO 只能查询当前登录用户下的列表
		return null;
	}

}
