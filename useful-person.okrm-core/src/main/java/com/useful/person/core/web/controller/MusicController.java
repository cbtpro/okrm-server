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

import com.useful.person.core.domain.Music;
import com.useful.person.core.services.impl.MusicServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/music")
@Api(value = "音乐controller", tags = { "音乐操作接口" } )
public class MusicController {

	@Autowired
	private MusicServiceImpl musicService;

	@ApiOperation("新增音乐")
	@PostMapping
	public Music addMusic(@RequestBody Music entity) {
		return musicService.saveOne(entity);
	}

	@ApiOperation("根据uuid删除音乐")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void deleteMusicByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
		musicService.deleteByUuid(uuid);
	}

	@ApiOperation("修改音乐")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Music updateMusic(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Music entity) {
		return musicService.saveOne(entity);
	}

	@ApiOperation("根据uuid查询音乐")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Music queryMusic(@PathVariable(name = "uuid", required = true) String uuid) {
		return musicService.findByUuid(uuid);
	}
}
