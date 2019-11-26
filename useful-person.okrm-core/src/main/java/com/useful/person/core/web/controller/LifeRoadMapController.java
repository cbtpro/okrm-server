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

import com.useful.person.core.domain.LifeRoadMap;
import com.useful.person.core.services.impl.LifeRoadMapServiceImpl;
import com.useful.person.core.vo.LifeRoadMapVO;

import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/life-road-map")
public class LifeRoadMapController {

	@Autowired
	private LifeRoadMapServiceImpl lifeRoadMapService;

	@ApiOperation("查询人生路线图")
	@GetMapping
	public LifeRoadMapVO queryMyLifeRoadMap() {
		return lifeRoadMapService.findAllMyLifeRoadMap();
	}

	@ApiOperation("新增人生路线图")
	@PostMapping
	public LifeRoadMap create(@RequestBody LifeRoadMap entity) {
		return lifeRoadMapService.saveOne(entity);
	}

	@ApiOperation("修改人生路线图")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public LifeRoadMap updateLifeRoadMap(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody LifeRoadMap entity) {
		return lifeRoadMapService.saveOne(entity);
	}

	@ApiOperation("删除人生路线")
	@DeleteMapping
	public void delete(@PathVariable(name = "uuid", required = true) String uuid) {
		lifeRoadMapService.deleteByUuid(uuid);
	}
}
