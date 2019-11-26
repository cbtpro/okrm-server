/**
 * 
 */
package com.useful.person.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.domain.Major;
import com.useful.person.core.services.impl.MajorServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/major")
public class MajorController {

	@Autowired
	private MajorServiceImpl majorService;

	@ApiOperation("根据uuid查询大学专业")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Major query(@PathVariable(name = "uuid", required = true) String uuid) {
		return majorService.findByUuid(uuid);
	}

	@ApiOperation("新增大学专业")
	@PostMapping
	public Major addMajor(@RequestBody Major entity) {
		return majorService.saveOne(entity);
	}

	@ApiOperation("修改大学专业")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Major updateMajor(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Major entity) {
		return majorService.saveOne(entity);
	}

	@ApiOperation("删除大学专业")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void deleteMajor(@PathVariable(name = "uuid", required = true) String uuid) {
		majorService.deleteByUuid(uuid);
	}
}
