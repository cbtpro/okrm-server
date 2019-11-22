/**
 * 
 */
package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.domain.ChinaAdultCollegeAndUniversity;
import com.useful.person.exception.ChinaAdultCollegeAndUniversityNotExistException;
import com.useful.person.repository.ChinaAdultCollegeAndUniversityRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.ChinaAdultCollegeAndUniversityService;

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
	public void deleteAll() {
		chinaAdultCollegeAndUniversityRepository.deleteAll();
	}

}
