/**
 * 
 */
package com.useful.person.core.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.annotation.HasAdminRole;
import com.useful.person.core.domain.ChinaAdultCollegeAndUniversity;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.services.impl.ChinaAdultCollegeAndUniversityServiceImpl;

import io.micrometer.core.instrument.util.StringUtils;
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

	@ApiOperation("查询所有中国成人高校列表")
	@GetMapping("/all")
	public List<ChinaAdultCollegeAndUniversity> queryAll() {
		return chinaAdultCollegeAndUniversityService.findAll();
	}

	@ApiOperation("查询所有中国成人高校位置信息")
	@GetMapping
	public Page<ChinaAdultCollegeAndUniversity> queryAllChinaAdultCollegesAndUniversityByPageable(
			@RequestParam(name = "name", required = false) String name,
			@PageableDefault(value = 15, sort = { "name" }, direction = Sort.Direction.ASC) Pageable pageable) {
		if (StringUtils.isBlank(name)) {
			return chinaAdultCollegeAndUniversityService.findAll(pageable);
		}
		return chinaAdultCollegeAndUniversityService.findByNameLike("%" + name + "%", pageable);
	}
	@ApiOperation("批量新增/更新中国成人高校信息")
	@HasAdminRole
	@PostMapping("/batch")
	public List<ChinaAdultCollegeAndUniversity> addAll(@RequestBody List<ChinaAdultCollegeAndUniversity> entities) {
		return chinaAdultCollegeAndUniversityService.saveAll(entities);
	}

	@ApiOperation("根据uuid获取中国成人高校信息")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public ChinaAdultCollegeAndUniversity queryById(@PathVariable(name = "uuid", required = true) String uuid) {
		return chinaAdultCollegeAndUniversityService.findByUuid(uuid);
	}

	@ApiOperation("根据uuid更新中国成人高校信息")
	@HasAdminRole
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public ChinaAdultCollegeAndUniversity updateOne(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody ChinaAdultCollegeAndUniversity entity) {
		entity.setUuid(uuid);
		return chinaAdultCollegeAndUniversityService.saveOne(entity);
	}
	
	@ApiOperation("新增中国成人高校信息")
	@HasAdminRole
	@PostMapping
	public ChinaAdultCollegeAndUniversity addOne(@RequestBody ChinaAdultCollegeAndUniversity entity) {
		return chinaAdultCollegeAndUniversityService.saveOne(entity);
	}
}
