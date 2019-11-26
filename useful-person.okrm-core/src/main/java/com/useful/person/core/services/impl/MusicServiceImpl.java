/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Music;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.repository.MusicRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.MusicService;

/**
 * @author peter
 *
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService, BasicService<Music> {

	@Autowired
	private MusicRepository musicRepository;

	@Override
	public Music saveOne(Music entity) {
		return musicRepository.save(entity);
	}

	@Override
	public List<Music> saveAll(List<Music> entities) {
		return musicRepository.saveAll(entities);
	}

	@Override
	public Music findByUuid(String uuid) {
		return musicRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("uuid", "music not found"));
	}

	@Override
	public List<Music> findAll() {
		return musicRepository.findAll();
	}

	@Override
	public void deleteOne(Music entity) {
		musicRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		musicRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		musicRepository.deleteAll();
	}

}
