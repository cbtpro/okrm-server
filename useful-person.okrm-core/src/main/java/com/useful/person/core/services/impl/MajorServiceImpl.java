/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Major;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.MajorRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.MajorService;

/**
 * @author peter
 *
 */
@Service("majorService")
public class MajorServiceImpl implements MajorService, BasicService<Major> {

    @Autowired
    private MajorRepository majorRepository;

    @Override
    public Major saveOne(Major entity) {
        return majorRepository.save(entity);
    }

    @Override
    public List<Major> saveAll(List<Major> entities) {
        return majorRepository.saveAll(entities);
    }

    @Override
    public Major findByUuid(String uuid) {
        return majorRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(uuid, "major not found"));
    }

    @Override
    public List<Major> findAll() {
        return majorRepository.findAll();
    }

    @Override
    public void deleteOne(Major entity) {
        majorRepository.delete(entity);
    }

    @Override
    public void deleteByUuid(String uuid) {
        majorRepository.deleteById(uuid);
    }

    @Override
    public void deleteAll() {
        majorRepository.deleteAll();
    }

}
