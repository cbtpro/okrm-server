/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.ChinaAdultCollegeAndUniversity;
import com.useful.person.core.exception.ChinaAdultCollegeAndUniversityNotExistException;
import com.useful.person.core.repository.ChinaAdultCollegeAndUniversityRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.ChinaAdultCollegeAndUniversityService;

/**
 * @author peter
 *
 */
@Service("chinaAdultCollegeAndUniversityService")
public class ChinaAdultCollegeAndUniversityServiceImpl implements ChinaAdultCollegeAndUniversityService, BasicService<ChinaAdultCollegeAndUniversity> {

	@Autowired
	private ChinaAdultCollegeAndUniversityRepository chinaAdultCollegeAndUniversityRepository;

	@Override
	public ChinaAdultCollegeAndUniversity findByUuid(String uuid) {
		return chinaAdultCollegeAndUniversityRepository.findById(uuid).orElseThrow(() -> new ChinaAdultCollegeAndUniversityNotExistException(uuid));
	}

	@Override
	public Page<ChinaAdultCollegeAndUniversity> findByNameLike(String name, Pageable pageable) {
		return chinaAdultCollegeAndUniversityRepository.findByNameLike(name, pageable);
	}

	@Override
	public List<ChinaAdultCollegeAndUniversity> saveAll(List<ChinaAdultCollegeAndUniversity> entities) {
		return chinaAdultCollegeAndUniversityRepository.saveAll(entities);
	}

	@Override
	public ChinaAdultCollegeAndUniversity saveOne(ChinaAdultCollegeAndUniversity entity) {
		return chinaAdultCollegeAndUniversityRepository.save(entity);
	}

	@Override
	public void deleteOne(ChinaAdultCollegeAndUniversity entity) {
		chinaAdultCollegeAndUniversityRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		chinaAdultCollegeAndUniversityRepository.deleteById(uuid);
	}

	@Override
	public List<ChinaAdultCollegeAndUniversity> findAll() {
		return chinaAdultCollegeAndUniversityRepository.findAll();
	}

	@Override
	public Page<ChinaAdultCollegeAndUniversity> findAll(Pageable pageable) {
		return chinaAdultCollegeAndUniversityRepository.findAll(pageable);
	}

	@Override
	public void deleteAll() {
		chinaAdultCollegeAndUniversityRepository.deleteAll();
	}

}
