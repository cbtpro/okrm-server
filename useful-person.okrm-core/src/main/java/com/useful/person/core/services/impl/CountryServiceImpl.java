/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Country;
import com.useful.person.core.repository.CountryRepository;
import com.useful.person.core.services.CountryService;

/**
 * @author cbtpro
 *
 */
@Service(value = "countryService")
@CacheConfig(cacheNames = "country")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Country> saveAll(List<Country> countrys) {
        return countryRepository.saveAll(countrys);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

}
