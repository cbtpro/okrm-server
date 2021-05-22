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

import com.useful.person.core.domain.Event;
import com.useful.person.core.services.impl.EventServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/event")
@Api(value = "事件controller", tags = { "事件操作接口" })
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    /**
     * 查询任务
     * 
     * @return
     */
    @ApiOperation("根据uuid查询事件")
    @GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Event event(@PathVariable(name = "uuid", required = true) String uuid) {
        return eventService.findByUuid(uuid);
    }

    /**
     * 创建任务
     * 
     * @param task
     * @return task
     */
    @ApiOperation(value = "新增事件")
    @PostMapping
    public Event createTask(@RequestBody Event event) {
        return eventService.saveOne(event);
    }

    /**
     * 更新任务
     * 
     * @param task
     * @return task
     */
    @ApiOperation(value = "修改事件")
    @PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public Event updateTask(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody Event event) {
        return eventService.saveOne(event);
    }

    @ApiOperation("删除事件")
    @DeleteMapping(ControllerConstants.PATH_UUID_SUFFIX)
    public void delete(@PathVariable(name = "uuid", required = true) String uuid) {
        eventService.deleteByUuid(uuid);
    }
}
