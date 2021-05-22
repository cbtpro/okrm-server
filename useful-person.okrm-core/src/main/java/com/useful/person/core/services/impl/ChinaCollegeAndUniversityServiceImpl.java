/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@CacheConfig(cacheNames = "chinaCollegeAndUniversity")
public class ChinaCollegeAndUniversityServiceImpl
        implements ChinaCollegeAndUniversityService, BasicService<ChinaCollegeAndUniversity> {

    @Autowired
    private ChinaCollegeAndUniversityRepository chinaCollegesAndUniversitiesRepository;

    @Override
    @Cacheable
    public ChinaCollegeAndUniversity findByUuid(String uuid) {
        return chinaCollegesAndUniversitiesRepository.findById(uuid)
                .orElseThrow(() -> new ChinaCollegeAndUniversityNotExistException(uuid));
    }

    @Override
    @Cacheable
    public Page<ChinaCollegeAndUniversity> findByNameLike(String name, Pageable pageable) {
        return chinaCollegesAndUniversitiesRepository.findByNameLike(name, pageable);
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
    @Cacheable
    public List<ChinaCollegeAndUniversity> findAll() {
        return chinaCollegesAndUniversitiesRepository.findAll();
    }

    @Override
    @Cacheable
    public Page<ChinaCollegeAndUniversity> findAll(Pageable pageable) {
        return chinaCollegesAndUniversitiesRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        chinaCollegesAndUniversitiesRepository.deleteAll();
    }

}
