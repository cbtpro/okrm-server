/**
 * 
 */
package com.useful.person.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.services.impl.LifeRoadMapServiceImpl;
import com.useful.person.vo.LifeRoadMapVO;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/life/roadmap")
public class LifeRoadMapController {

	@Autowired
	private LifeRoadMapServiceImpl lifeRoadMapService;

	@ApiOperation("查询人生路线图")
	@GetMapping
	public LifeRoadMapVO queryMyLifeRoadMap() {
		return lifeRoadMapService.findAllMyLifeRoadMap();
	}

}
