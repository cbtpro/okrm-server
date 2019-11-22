/**
 * 
 */
package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.domain.LifeRoadMap;
import com.useful.person.domain.User;
import com.useful.person.exception.LifeRoadMapNotExistException;
import com.useful.person.repository.LifeRoadMapRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.LifeRoadMapService;
import com.useful.person.vo.LifeRoadMapVO;

/**
 * @author peter
 *
 */
@Service("lifeRoadMapService")
public class LifeRoadMapServiceImpl implements LifeRoadMapService, BasicService<LifeRoadMap> {

	@Autowired
	private LifeRoadMapRepository lifeRoadMapRepository;

	@Override
	public LifeRoadMap saveOne(LifeRoadMap entity) {
		return lifeRoadMapRepository.save(entity);
	}

	@Override
	public List<LifeRoadMap> saveAll(List<LifeRoadMap> entities) {
		return lifeRoadMapRepository.saveAll(entities);
	}

	@Override
	public LifeRoadMap findByUuid(String uuid) {
		return lifeRoadMapRepository.findById(uuid).orElseThrow(() -> new LifeRoadMapNotExistException(uuid));
	}

	@Override
	public List<LifeRoadMap> findAll() {
		return lifeRoadMapRepository.findAll();
	}

	@Override
	public void deleteOne(LifeRoadMap entity) {
		lifeRoadMapRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		lifeRoadMapRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		lifeRoadMapRepository.deleteAll();
	}

	public LifeRoadMapVO findAllMyLifeRoadMap() {
		List<LifeRoadMap> list = lifeRoadMapRepository.findByUser(User.builder().username("cbtpro").build());
		LifeRoadMapVO mylifeRoadMayVO = LifeRoadMapVO.builder().nickname("nickname").lifeRoadMaps(list).build();
		return mylifeRoadMayVO;
	}
}
