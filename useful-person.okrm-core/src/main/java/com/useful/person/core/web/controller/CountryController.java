package com.useful.person.core.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.annotation.HasAdminRole;
import com.useful.person.core.domain.Country;
import com.useful.person.core.services.CountryService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/country")
@Api(value = "国家和地区controller", tags = { "国家和地区操作接口" } )
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping
	private List<Country> findAll() {
		return countryService.findAll();
	}

	@HasAdminRole
	@PutMapping("/batch")
	private List<Country> saveAll(@RequestBody List<Country> countrys) {
		return countryService.saveAll(countrys);
	}

	@HasAdminRole
	@PutMapping
	private Country save(@RequestBody Country country) {
		return countryService.save(country);
	}
	
}
