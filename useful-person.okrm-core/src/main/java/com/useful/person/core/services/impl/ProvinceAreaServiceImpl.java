/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.ProvinceArea;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.ProvinceAreaRepository;
import com.useful.person.core.services.ProvinceAreaService;

/**
 * @author cbtpro
 *
 */
@Service(value = "provinceAreaService")
@CacheConfig(cacheNames = "provinceArea")
public class ProvinceAreaServiceImpl implements ProvinceAreaService {

    @Autowired
    private ProvinceAreaRepository provinceAreaRepository;

    @Override
    public ProvinceArea save(ProvinceArea provinceArea) {
        return provinceAreaRepository.save(provinceArea);
    }

    @Override
    public List<ProvinceArea> saveAll(List<ProvinceArea> provinceAreas) {
        return provinceAreaRepository.saveAll(provinceAreas);
    }

    @Override
    public List<ProvinceArea> findAll() {
        return provinceAreaRepository.findAllByOrderByCode();
    }

    @Override
    public List<ProvinceArea> findChilds(String upperCode) {
        return provinceAreaRepository.findByUpperCodeOrderByCode(upperCode);
    }

    @Override
    public ProvinceArea findProvinceAreaByUuid(String uuid) {
        return provinceAreaRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(uuid, "省份信息不存在"));
    }

    @Override
    public ProvinceArea findProvinceAreaByCode(String code) {
        return provinceAreaRepository.findByCodeOrderByCode(code);
    }

}
