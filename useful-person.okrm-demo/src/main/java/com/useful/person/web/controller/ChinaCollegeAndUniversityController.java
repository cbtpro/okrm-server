/**
 * 
 */
package com.useful.person.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.domain.ChinaCollegeAndUniversity;
import com.useful.person.services.impl.ChinaCollegeAndUniversityServiceImpl;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/university")
public class ChinaCollegeAndUniversityController {

	@Autowired
	private ChinaCollegeAndUniversityServiceImpl chinaCollegesAndUniversitiesService;

	@GetMapping
	public List<ChinaCollegeAndUniversity> queryAllChinaCollegesAndUniversities() {
		return chinaCollegesAndUniversitiesService.findAll();
	}

	@PostMapping
	public List<ChinaCollegeAndUniversity> addAll(@RequestBody List<ChinaCollegeAndUniversity> list) {
		return chinaCollegesAndUniversitiesService.addAll(list);
	}
	
}
