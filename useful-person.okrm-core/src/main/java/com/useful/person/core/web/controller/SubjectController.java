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

import com.useful.person.core.domain.Subject;
import com.useful.person.core.services.impl.SubjectServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/subject")
@Api(value = "科目controller", tags = { "科目操作接口" } )
public class SubjectController {

	@Autowired
	private SubjectServiceImpl subjectService;

	@ApiOperation("新增科目")
	@PostMapping
	public Subject addSubject(@RequestBody Subject entity) {
		return subjectService.saveOne(entity);
	}

	@ApiOperation("根据uuid删除科目")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void deleteSubjectByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
		subjectService.deleteByUuid(uuid);
	}

	@ApiOperation("修改科目")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Subject updateSubject(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Subject entity) {
		return subjectService.saveOne(entity);
	}

	@ApiOperation("根据uuid查询科目")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Subject querySubject(@PathVariable(name = "uuid", required = true) String uuid) {
		return subjectService.findByUuid(uuid);
	}
}
