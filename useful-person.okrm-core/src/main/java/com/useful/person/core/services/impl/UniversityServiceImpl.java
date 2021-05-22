/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.University;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.UniversityRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.UniversityService;

/**
 * @author peter
 *
 */
@Service("universityService")
public class UniversityServiceImpl implements UniversityService, BasicService<University> {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University saveOne(University entity) {
        return universityRepository.save(entity);
    }

    @Override
    public List<University> saveAll(List<University> entities) {
        return universityRepository.saveAll(entities);
    }

    @Override
    public University findByUuid(String uuid) {
        return universityRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(uuid, "university not found"));
    }

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    public void deleteOne(University entity) {
        universityRepository.delete(entity);
    }

    @Override
    public void deleteByUuid(String uuid) {
        universityRepository.deleteById(uuid);
    }

    @Override
    public void deleteAll() {
        universityRepository.deleteAll();
    }

}
