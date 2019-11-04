/**
 * 
 */
package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.domain.ChinaCollegeAndUniversity;
import com.useful.person.exception.ChinaCollegeAndUniversityNotExistException;
import com.useful.person.repository.ChinaCollegeAndUniversityRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.ChinaCollegeAndUniversityService;

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
	public ChinaCollegeAndUniversity addOne(ChinaCollegeAndUniversity entity) {
		return chinaCollegesAndUniversitiesRepository.save(entity);
	}

	@Override
	public List<ChinaCollegeAndUniversity> addAll(List<ChinaCollegeAndUniversity> entities) {
		return chinaCollegesAndUniversitiesRepository.saveAll(entities);
	}

	@Override
	public ChinaCollegeAndUniversity updateOne(ChinaCollegeAndUniversity entity) {
		return chinaCollegesAndUniversitiesRepository.save(entity);
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

}