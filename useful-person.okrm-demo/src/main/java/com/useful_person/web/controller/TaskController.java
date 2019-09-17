package com.useful_person.web.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful_person.domain.Task;

@RestController
@RequestMapping("/task")
public class TaskController {

	/**
	 * 查询任务
	 * 
	 * @return
	 */
	@GetMapping("/{id:[0-9A-Za-z]{32}}")
	public Task task(@PathVariable(name = "id", required = true) String uuid) {
		Task task = Task.builder().uuid(uuid).build();
		return task;
	}

	/**
	 * 创建任务
	 * 
	 * @param task
	 * @return task
	 */
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		long now = new Date().getTime();
		Task newTask = Task.builder().uuid("abc").title(task.getTitle()).createTime(now).updateTime(now).build();
		return newTask;
	}

	/**
	 * 更新任务
	 * 
	 * @param task
	 * @return task
	 */
	@PutMapping("/{id:[0-9A-Za-z]{8}-[0-9A-Za-z]{4}-[0-9A-Za-z]{4}-[0-9A-Za-z]{4}-[0-9A-Za-z]{12}}")
	public Task updateTask(@PathVariable(name = "id", required = true) String uuid, @RequestBody Task task) {
		long now = new Date().getTime();
		Task updatedTask = Task.builder().uuid(uuid).title(task.getTitle()).updateTime(now).build();
		return updatedTask;
	}
}
