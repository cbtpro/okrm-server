/**
 * 
 */
package com.useful.person.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.domain.ChinaAdultCollegeAndUniversity;
import com.useful.person.services.impl.ChinaAdultCollegeAndUniversityServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/university/adult")
public class ChinaAdultCollegeAndUniversityController {

	@Autowired
	public ChinaAdultCollegeAndUniversityServiceImpl chinaAdultCollegeAndUniversityService;

	@ApiOperation("查询所有成人高校列表")
	@GetMapping
	public List<ChinaAdultCollegeAndUniversity> queryAll() {
		return chinaAdultCollegeAndUniversityService.findAll();
	}

	@ApiOperation("批量新增/更新成人高校信息")
	@PostMapping
	public List<ChinaAdultCollegeAndUniversity> addAll(@RequestBody List<ChinaAdultCollegeAndUniversity> entities) {
		return chinaAdultCollegeAndUniversityService.addAll(entities);
	}

	@ApiOperation("根据uuid获取成人高校信息")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public ChinaAdultCollegeAndUniversity queryById(@PathVariable(name = "uuid", required = true) String uuid) {
		return chinaAdultCollegeAndUniversityService.findByUuid(uuid);
	}

	@ApiOperation("根据uuid更新成人高校信息")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public ChinaAdultCollegeAndUniversity updateOne(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody ChinaAdultCollegeAndUniversity entity) {
		entity.setUuid(uuid);
		return chinaAdultCollegeAndUniversityService.updateOne(entity);
	}
}