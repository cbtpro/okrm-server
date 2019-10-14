package com.useful_person.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.useful_person.core.authentication.exception.UserNotExistException;
import com.useful_person.core.authentication.exception.UsernameExistException;
import com.useful_person.core.properties.OkrmConstants;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handleUserNotExistException(UserNotExistException userNotExistException) {
		Map<String, Object> result = new HashMap<>();
		result.put("uuid", userNotExistException.getUuid());
		result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, userNotExistException.getMessage());
		return result;
	}

	@ExceptionHandler(UsernameExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> handleUsernameExistException(UsernameExistException usernameExistException) {
		Map<String, Object> result = new HashMap<>();
		result.put("username",usernameExistException.getUsername());
		result.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, usernameExistException.getMessage());
		return result;
	}
}
