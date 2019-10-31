package com.useful.person.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.properties.AppConstants;
import com.useful.person.domain.Tag;
import com.useful.person.services.impl.TagServiceImpl;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagServiceImpl tagService;

	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Tag tag(@PathVariable(name = "uuid", required = true) String uuid) {
		return tagService.findByUuid(uuid);
	}

	@PostMapping
	public Tag createTask(@RequestBody Tag tag) {
		return tagService.addOne(tag);
	}

	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Tag updateTag(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody Tag tag) {
		return tagService.updateOne(tag);
	}

	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Map<String, String> deleteTag(@PathVariable(name = "uuid", required = true) String uuid) {
		Map<String, String> result = new HashMap<String, String>(2);
		tagService.deleteByUuid(uuid);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "删除成功");
		result.put("uuid", uuid);
		return result;
	}
}
