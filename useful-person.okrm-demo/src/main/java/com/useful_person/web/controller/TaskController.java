package com.useful_person.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.useful_person.domain.Task;

@RestController
public class TaskController {

	/**
	 * 查询任务
	 * @return 
	 */
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public List<Task> query() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task());
		tasks.add(new Task());
		tasks.add(new Task());
		return tasks;
	}

	/**
	 * 创建任务
	 * @param task
	 * @return task
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Task createTask(Task task) {
		return task;
	}
	
	/**
	 * 更新任务
	 * @param task
	 * @return task
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public Task updateTask(Task task) {
		return task;
	}
}
