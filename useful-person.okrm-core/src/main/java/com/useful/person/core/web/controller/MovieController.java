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

import com.useful.person.core.domain.Movie;
import com.useful.person.core.services.impl.MovieServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/movie")
@Api(value = "影视controller", tags = { "影视操作接口" } )
public class MovieController {

	@Autowired
	private MovieServiceImpl movieService;

	@ApiOperation("根据uuid查询电影")
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Movie queryByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
		return movieService.findByUuid(uuid);
	}

	@ApiOperation("新增Movie")
	@PostMapping
	public Movie createMovie(@RequestBody Movie entity) {
		return movieService.saveOne(entity);
	}

	@ApiOperation("修改电影")
	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public Movie updateMovie(@PathVariable(name = "uuid", required = true) String uuid,
			@RequestBody Movie entity) {
		return movieService.saveOne(entity);
	}

	@ApiOperation("删除电影")
	@DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void deleteMovie(@PathVariable(name = "uuid", required = true) String uuid) {
		movieService.deleteByUuid(uuid);
	}
}
