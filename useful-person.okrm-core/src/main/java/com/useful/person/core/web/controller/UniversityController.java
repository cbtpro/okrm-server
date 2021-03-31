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

import com.useful.person.core.domain.University;
import com.useful.person.core.services.impl.UniversityServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/college")
@Api(value = "学院controller", tags = { "学院操作接口" } )
public class UniversityController {

	@Autowired
	private UniversityServiceImpl universityService;

	@ApiOperation("新增学院")
	@PostMapping
	public University addUniversity(@RequestBody University entity) {
		return universityService.saveOne(entity);
	}

	@ApiOperation("根据uuid删除学院")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void deleteUniversityByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
		universityService.deleteByUuid(uuid);
	}

	@ApiOperation("修改学院")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public University updateUniversity(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody University entity) {
		return universityService.saveOne(entity);
	}

	@ApiOperation("根据uuid查询学院")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public University queryUniversity(@PathVariable(name = "uuid", required = true) String uuid) {
		return universityService.findByUuid(uuid);
	}
}
