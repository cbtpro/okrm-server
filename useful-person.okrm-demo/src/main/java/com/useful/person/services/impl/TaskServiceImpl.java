package com.useful.person.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.domain.Task;
import com.useful.person.exception.TaskNotExistException;
import com.useful.person.repository.TaskRepository;
import com.useful.person.services.BasicService;
import com.useful.person.services.TaskService;

/**
 * 
 * @author peter
 *
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService, BasicService<Task> {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task findByUuid(String uuid) {
		return taskRepository.findById(uuid).orElseThrow(() -> new TaskNotExistException(uuid));
	}

	@Override
	public Task addOne(Task entity) {
		return taskRepository.save(entity);
	}

	@Override
	public List<Task> addAll(List<Task> entities) {
		return taskRepository.saveAll(entities);
	}

	@Override
	public Task updateOne(Task entity) {
		return taskRepository.save(entity);
	}

	@Override
	public void deleteOne(Task entity) {
		taskRepository.delete(entity);
	}

	@Override
	public void deleteByUuid(String uuid) {
		taskRepository.deleteById(uuid);
	}

	@Override
	public List<Task> findAll() {
		// TODO 只能查询当前登录用户下的列表
		return null;
	}

}
