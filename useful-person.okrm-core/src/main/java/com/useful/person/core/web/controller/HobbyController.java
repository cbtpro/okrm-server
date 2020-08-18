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

import com.useful.person.core.domain.Hobby;
import com.useful.person.core.services.impl.HobbyServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/hobby")
public class HobbyController {

	@Autowired
	private HobbyServiceImpl hobbyService;

	@ApiOperation("根据uuid查询兴趣爱好")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Hobby hobby(@PathVariable(name = "uuid", required = true) String uuid) {
		return hobbyService.findByUuid(uuid);
	}

	@ApiOperation("新增兴趣好爱")
	@PostMapping
	public Hobby createHobby(@RequestBody Hobby entity) {
		return hobbyService.saveOne(entity);
	}

	@ApiOperation("修改兴趣爱好")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Hobby updateHobby(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Hobby entity) {
		return hobbyService.saveOne(entity);
	}
	
	@ApiOperation("删除兴趣爱好")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void delete(@PathVariable(name = "uuid", required = true) String uuid) {
		hobbyService.deleteByUuid(uuid);
	}
}
