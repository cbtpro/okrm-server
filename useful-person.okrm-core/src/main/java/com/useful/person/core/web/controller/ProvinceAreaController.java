/**
 * 
 */
package com.useful.person.core.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.domain.ProvinceArea;
import com.useful.person.core.services.ProvinceAreaService;

/**
 * @author cbtpro
 *
 */
@RestController
@RequestMapping("/province")
public class ProvinceAreaController {

	@Autowired
	private ProvinceAreaService provinceAreaService;

	@GetMapping
	private List<ProvinceArea> findAll() {
		return provinceAreaService.findAll();
	}

	@PutMapping("/batch")
	private List<ProvinceArea> saveAll(@RequestBody List<ProvinceArea> provinceAreas) {
		return provinceAreaService.saveAll(provinceAreas);
	}

	@PutMapping
	private ProvinceArea save(@RequestBody ProvinceArea provinceArea) {
		return provinceAreaService.save(provinceArea);
	}

//	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
//	private ProvinceArea findProvinceAreaByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
//		return provinceAreaService.findProvinceAreaByUuid(uuid);
//	}

	@GetMapping("/query")
	private ProvinceArea findProvinceArea(@Param(value = "code") String code) {
		return provinceAreaService.findProvinceAreaByCode(code);
	}

	@GetMapping("/child")
	private List<ProvinceArea> findChilds(@Param(value = "upperCode") String upperCode) {
		return provinceAreaService.findChilds(StringUtils.isNotBlank(upperCode) ? upperCode : "86");
	}
}
