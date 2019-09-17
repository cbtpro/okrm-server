package com.useful_person.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.useful_person.exception.UserNotExistException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handleUserNotExistException(UserNotExistException userNotExistException) {
		Map<String, Object> result = new HashMap<>();
		result.put("uuid", userNotExistException.getUuid());
		result.put("message", userNotExistException.getMessage());
		return result;
	}
}
