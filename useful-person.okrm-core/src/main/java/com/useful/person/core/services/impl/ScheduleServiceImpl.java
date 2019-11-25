/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Schedule;
import com.useful.person.core.exception.ScheduleNotExistException;
import com.useful.person.core.repository.ScheduleRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.ScheduleService;

/**
 * @author peter
 *
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService, BasicService<Schedule> {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Schedule findByUuid(String uuid) {
		return scheduleRepository.findById(uuid).orElseThrow(() -> new ScheduleNotExistException(uuid));
	}

	@Override
	public List<Schedule> findAll() {
		return null;
	}

	@Override
	public Schedule saveOne(Schedule entity) {
		return scheduleRepository.save(entity);
	}

	@Override
	public List<Schedule> saveAll(List<Schedule> entities) {
		return null;
	}

	@Override
	public void deleteOne(Schedule entity) {
		scheduleRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		scheduleRepository.deleteById(uuid);
	}

	@Override
	public void deleteAll() {
		scheduleRepository.deleteAll();
	}
}
