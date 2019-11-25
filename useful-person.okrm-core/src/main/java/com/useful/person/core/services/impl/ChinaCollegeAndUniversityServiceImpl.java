/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.ChinaCollegeAndUniversity;
import com.useful.person.core.exception.ChinaCollegeAndUniversityNotExistException;
import com.useful.person.core.repository.ChinaCollegeAndUniversityRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.ChinaCollegeAndUniversityService;

/**
 * @author peter
 *
 */
@Service("chinaCollegesAndUniversitiesService")
public class ChinaCollegeAndUniversityServiceImpl implements ChinaCollegeAndUniversityService, BasicService<ChinaCollegeAndUniversity> {

	@Autowired
	private ChinaCollegeAndUniversityRepository chinaCollegesAndUniversitiesRepository;

	@Override
	public ChinaCollegeAndUniversity findByUuid(String uuid) {
		return chinaCollegesAndUniversitiesRepository.findById(uuid).orElseThrow(() -> new ChinaCollegeAndUniversityNotExistException(uuid));
	}

	@Override
	public ChinaCollegeAndUniversity saveOne(ChinaCollegeAndUniversity entity) {
		return chinaCollegesAndUniversitiesRepository.save(entity);
	}

	@Override
	public List<ChinaCollegeAndUniversity> saveAll(List<ChinaCollegeAndUniversity> entities) {
		return chinaCollegesAndUniversitiesRepository.saveAll(entities);
	}

	@Override
	public void deleteOne(ChinaCollegeAndUniversity entity) {
		chinaCollegesAndUniversitiesRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		chinaCollegesAndUniversitiesRepository.deleteById(uuid);
	}

	@Override
	public List<ChinaCollegeAndUniversity> findAll() {
		return chinaCollegesAndUniversitiesRepository.findAll();
	}

	@Override
	public void deleteAll() {
		chinaCollegesAndUniversitiesRepository.deleteAll();
	}

}
