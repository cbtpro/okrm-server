/**
 * 
 */
package com.useful.person.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.domain.Schedule;
import com.useful.person.core.services.impl.ScheduleServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleServiceImpl scheduleService;

	@ApiOperation("根据UUID查询日程")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Schedule queryById(@PathVariable(name = "uuid", required = true) String uuid) {
		return scheduleService.findByUuid(uuid);
	}

	@ApiOperation("新增日程")
	@PostMapping
	public Schedule add(@RequestBody Schedule entity) {
		return scheduleService.saveOne(entity);
	}

	@ApiOperation("根据uuid更新日程")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Schedule updateOne(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Schedule entity) {
		return scheduleService.saveOne(entity);
	}

}
