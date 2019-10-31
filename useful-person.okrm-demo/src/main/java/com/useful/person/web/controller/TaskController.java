package com.useful.person.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.domain.Task;
import com.useful.person.services.impl.TaskServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskServiceImpl taskService;

	/**
	 * 查询任务
	 * 
	 * @return
	 */
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Task task(@PathVariable(name = "uuid", required = true) String uuid) {
		return taskService.findByUuid(uuid);
	}

	/**
	 * 创建任务
	 * 
	 * @param task
	 * @return task
	 */
	@ApiOperation(value = "新增一个task")
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		Task returnTask = taskService.addOne(task);
		return returnTask;
	}

	/**
	 * 更新任务
	 * 
	 * @param task
	 * @return task
	 */
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Task updateTask(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody Task task) {
		Task updatedTask = taskService.updateOne(task);
		return updatedTask;
	}
}
