/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Slogon;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.SlogonRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.SlogonService;

/**
 * @author peter
 *
 */
@Service("slogonService")
public class SlogonServiceImpl implements SlogonService, BasicService<Slogon> {

	@Autowired
	private SlogonRepository slogonRepository;

	@Override
	public Slogon saveOne(Slogon entity) {
		return slogonRepository.save(entity);
	}

	@Override
	public List<Slogon> saveAll(List<Slogon> entities) {
		return slogonRepository.saveAll(entities);
	}

	@Override
	public Slogon findByUuid(String uuid) {
		return slogonRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(uuid, "slogon not found"));
	}

	@Override
	public List<Slogon> findAll() {
		return slogonRepository.findAll();
	}

	@Override
	public void deleteOne(Slogon entity) {
		slogonRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		slogonRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
