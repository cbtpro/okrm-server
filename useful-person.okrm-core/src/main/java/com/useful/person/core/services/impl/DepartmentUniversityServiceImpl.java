/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.DepartmentUniversity;
import com.useful.person.core.exception.DepartmentUniversityNotExistException;
import com.useful.person.core.repository.DepartmentUniversityRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.DepartmentUniversityService;

/**
 * @author peter
 *
 */
@Service("departmentUniversityService")
public class DepartmentUniversityServiceImpl implements DepartmentUniversityService, BasicService<DepartmentUniversity> {

	@Autowired
	private DepartmentUniversityRepository departmentUniversityRepository;

	@Override
	public DepartmentUniversity saveOne(DepartmentUniversity entity) {
		return departmentUniversityRepository.save(entity);
	}

	@Override
	public List<DepartmentUniversity> saveAll(List<DepartmentUniversity> entities) {
		return departmentUniversityRepository.saveAll(entities);
	}

	@Override
	public DepartmentUniversity findByUuid(String uuid) {
		return departmentUniversityRepository.findById(uuid).orElseThrow(() -> new DepartmentUniversityNotExistException(uuid));
	}

	@Override
	public List<DepartmentUniversity> findAll() {
		return departmentUniversityRepository.findAll();
	}

	@Override
	public void deleteOne(DepartmentUniversity entity) {
		departmentUniversityRepository.delete(entity);		
	}

	@Override
	public void deleteByUuid(String uuid) {
		departmentUniversityRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		departmentUniversityRepository.deleteAll();
	}

}
