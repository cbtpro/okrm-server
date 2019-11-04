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

import com.useful.person.domain.ChinaCollegeAndUniversity;
import com.useful.person.services.impl.ChinaCollegeAndUniversityServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/university")
public class ChinaCollegeAndUniversityController {

	@Autowired
	private ChinaCollegeAndUniversityServiceImpl chinaCollegesAndUniversitiesService;

	@ApiOperation("查询所有中国高校列表")
	@GetMapping
	public List<ChinaCollegeAndUniversity> queryAllChinaCollegesAndUniversities() {
		return chinaCollegesAndUniversitiesService.findAll();
	}

//	@ApiOperation("批量增加/更新中国高校")
//	@PostMapping
//	public List<ChinaCollegeAndUniversity> addAll(@RequestBody List<ChinaCollegeAndUniversity> list) {
//		return chinaCollegesAndUniversitiesService.addAll(list);
//	}

	@ApiOperation("根据UUID查询高校列表")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public ChinaCollegeAndUniversity queryById(@PathVariable(name = "uuid", required = true) String uuid) {
		return chinaCollegesAndUniversitiesService.findByUuid(uuid);
	}

	@ApiOperation("根据uuid更新中国高校")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public ChinaCollegeAndUniversity updateOne(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody ChinaCollegeAndUniversity entity) {
		return chinaCollegesAndUniversitiesService.addOne(entity);
	}

	@ApiOperation("新增中国高校")
	@PostMapping
	public ChinaCollegeAndUniversity add(@RequestBody ChinaCollegeAndUniversity entity) {
		return chinaCollegesAndUniversitiesService.addOne(entity);
	}
}
