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

import com.useful.person.core.domain.Vocation;
import com.useful.person.core.services.impl.VocationServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/vocation")
@Api(value = "职业controller", tags = { "职业操作接口" })
public class VocationController {

    @Autowired
    private VocationServiceImpl vocationService;

    @ApiOperation("新增职业")
    @PostMapping
    public Vocation addVocation(@RequestBody Vocation entity) {
        return vocationService.saveOne(entity);
    }

    @ApiOperation("根据uuid删除职业")
    @DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public void deleteVocationByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
        vocationService.deleteByUuid(uuid);
    }

    @ApiOperation("修改职业")
    @PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Vocation updateVocation(@PathVariable(name = "uuid", required = true) String uuid,
            @RequestBody Vocation entity) {
        return vocationService.saveOne(entity);
    }

    @ApiOperation("根据uuid查询职业")
    @GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Vocation queryUniversity(@PathVariable(name = "uuid", required = true) String uuid) {
        return vocationService.findByUuid(uuid);
    }
}
