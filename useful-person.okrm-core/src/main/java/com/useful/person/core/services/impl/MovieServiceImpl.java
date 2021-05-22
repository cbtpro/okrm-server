/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Movie;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.MovieRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.MovieService;

/**
 * @author peter
 *
 */
@Service("movieService")
public class MovieServiceImpl implements MovieService, BasicService<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie saveOne(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public List<Movie> saveAll(List<Movie> entities) {
        return movieRepository.saveAll(entities);
    }

    @Override
    public Movie findByUuid(String uuid) {
        return movieRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(uuid, "movie not found"));
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void deleteOne(Movie entity) {
        movieRepository.delete(entity);
    }

    @Override
    public void deleteByUuid(String uuid) {
        movieRepository.deleteById(uuid);
    }

    @Override
    public void deleteAll() {
        movieRepository.deleteAll();
    }

}
