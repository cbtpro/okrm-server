package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.domain.Tag;
import com.useful.person.core.services.impl.TagServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/tag")
@Api(value = "标签controller", tags = { "标签操作接口" })
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    @ApiOperation("根据uuid查询tag")
    @GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Tag tag(@PathVariable(name = "uuid", required = true) String uuid) {
        return tagService.findByUuid(uuid);
    }

    @ApiOperation("新增tag")
    @PostMapping
    public Tag createTask(@RequestBody Tag tag) {
        return tagService.saveOne(tag);
    }

    @ApiOperation("根据uuid更新tag")
    @PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Tag updateTag(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody Tag tag) {
        return tagService.saveOne(tag);
    }

    @ApiOperation("根据uuid删除tag")
    @DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Map<String, String> deleteTag(@PathVariable(name = "uuid", required = true) String uuid) {
        Map<String, String> result = new HashMap<String, String>(2);
        tagService.deleteByUuid(uuid);
        result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "删除成功");
        result.put("uuid", uuid);
        return result;
    }
}
