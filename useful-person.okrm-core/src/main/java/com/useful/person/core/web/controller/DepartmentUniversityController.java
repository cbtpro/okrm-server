/**
 * 
 */
package com.useful.person.core.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.annotation.HasAdminRole;
import com.useful.person.core.domain.DepartmentUniversity;
import com.useful.person.core.services.impl.DepartmentUniversityServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/department-university")
public class DepartmentUniversityController {

	@Autowired
	private DepartmentUniversityServiceImpl departmentUniversityService;

	@ApiOperation("查询所有大学的院系结构")
	@HasAdminRole
	@GetMapping
	public List<DepartmentUniversity> queryAll() {
		return departmentUniversityService.findAll();
	}

	@ApiOperation("批量新增/更新大学的院系结构")
	@HasAdminRole
	@PostMapping("/batch")
	public List<DepartmentUniversity> saveAll(@RequestBody List<DepartmentUniversity> list) {
		return departmentUniversityService.saveAll(list);
	}

	@ApiOperation("根据uuid查询大学院系结构")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public DepartmentUniversity queryByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
		return departmentUniversityService.findByUuid(uuid);
	}

	@ApiOperation("根据uuid更新大学院系结构")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public DepartmentUniversity updateOne(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody DepartmentUniversity entity) {
		return departmentUniversityService.saveOne(entity);
	}

	@ApiOperation("新增大学院系结构")
	@HasAdminRole
	@PostMapping
	public DepartmentUniversity add(@RequestBody DepartmentUniversity entity) {
		return departmentUniversityService.saveOne(entity);
	}
}
