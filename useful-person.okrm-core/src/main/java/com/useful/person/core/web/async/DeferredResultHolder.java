package com.useful.person.core.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 
 * @author peter
 *
 */
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> taskMap = new HashMap<String, DeferredResult<String>>();

    public Map<String, DeferredResult<String>> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<String, DeferredResult<String>> taskMap) {
        this.taskMap = taskMap;
    }

}
