package com.useful_person.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.useful_person.domain.Task;

@RestController
@RequestMapping("/tasks")
public class TasksController {

	@GetMapping
	public List<Task> queryTasks(@RequestParam(name = "title", required = true) String title) {
		List<Task> tasks = new ArrayList<>();
		tasks.add(Task.builder().uuid("abc").title("走西口").build());
		tasks.add(Task.builder().uuid("bca").title("一起走西口").build());
		tasks.add(Task.builder().uuid("bac").title("大家来走西口").build());
		return tasks;
	}

}
