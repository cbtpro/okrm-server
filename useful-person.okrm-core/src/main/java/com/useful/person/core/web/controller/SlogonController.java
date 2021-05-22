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

import com.useful.person.core.domain.Slogon;
import com.useful.person.core.services.impl.SlogonServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/slogon")
@Api(value = "口号controller", tags = { "口号操作接口" })
public class SlogonController {

    @Autowired
    private SlogonServiceImpl slogonService;

    @ApiOperation("新增slogon")
    @PostMapping
    public Slogon addSlogon(@RequestBody Slogon entity) {
        return slogonService.saveOne(entity);
    }

    @ApiOperation("根据uuid删除slogon")
    @DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public void deleteSlogonByUuid(@PathVariable(name = "uuid", required = true) String uuid) {
        slogonService.deleteByUuid(uuid);
    }

    @ApiOperation("修改slogon")
    @PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Slogon updateSlogon(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody Slogon entity) {
        return slogonService.saveOne(entity);
    }

    @ApiOperation("根据uuid查询slogon")
    @GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Slogon querySlogon(@PathVariable(name = "uuid", required = true) String uuid) {
        return slogonService.findByUuid(uuid);
    }
}
