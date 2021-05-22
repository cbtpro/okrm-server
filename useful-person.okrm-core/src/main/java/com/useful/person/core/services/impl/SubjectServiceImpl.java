/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Subject;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.SubjectRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.SubjectService;

/**
 * @author peter
 *
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService, BasicService<Subject> {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject saveOne(Subject entity) {
        return subjectRepository.save(entity);
    }

    @Override
    public List<Subject> saveAll(List<Subject> entities) {
        return subjectRepository.saveAll(entities);
    }

    @Override
    public Subject findByUuid(String uuid) {
        return subjectRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException(uuid, "subject not found"));
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void deleteOne(Subject entity) {
        subjectRepository.delete(entity);
    }

    @Override
    public void deleteByUuid(String uuid) {
        subjectRepository.deleteById(uuid);
    }

    @Override
    public void deleteAll() {
        subjectRepository.deleteAll();
    }

}
