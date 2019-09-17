package com.useful_person.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

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
