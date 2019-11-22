package com.useful.person.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.useful.person.core.authentication.exception.MobileNotRegisteredException;
import com.useful.person.core.authentication.exception.UserNotExistException;
import com.useful.person.core.authentication.exception.UsernameExistException;
import com.useful.person.core.exception.SenderMailException;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.exception.ChinaAdultCollegeAndUniversityNotExistException;
import com.useful.person.exception.ChinaCollegeAndUniversityNotExistException;
import com.useful.person.exception.EventNotExistException;

/**
 * 
 * @author peter
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleUserNotExistException(UserNotExistException userNotExistException) {
		Map<String, String> result = new HashMap<>(2);
		result.put("uuid", userNotExistException.getUuid());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, userNotExistException.getMessage());
		return result;
	}

	@ExceptionHandler(UsernameExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> handleUsernameExistException(UsernameExistException usernameExistException) {
		Map<String, String> result = new HashMap<>(2);
		result.put("username", usernameExistException.getUsername());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, usernameExistException.getMessage());
		return result;
	}

	@ExceptionHandler(MobileNotRegisteredException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMobileNotRegisteredException(MobileNotRegisteredException e) {
		Map<String, String> result = new HashMap<>(2);
		result.put("mobile", e.getMobile());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		return result;
	}

	@ExceptionHandler(EventNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handlerTaskNotExistExcetpion(EventNotExistException taskNotExistException) {
		Map<String, String> result = new HashMap<>(2);
		result.put("uuid", taskNotExistException.getUuid());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, taskNotExistException.getMessage());
		return result;
	}

	@ExceptionHandler(ChinaCollegeAndUniversityNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handlerChinaCollegeAndUniversityNotExistException(
			ChinaCollegeAndUniversityNotExistException e) {
		Map<String, String> result = new HashMap<>(2);
		result.put("uuid", e.getUuid());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		return result;
	}

	@ExceptionHandler(ChinaAdultCollegeAndUniversityNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handlerChinaAdultCollegeAndUniversityNotExistException(
			ChinaAdultCollegeAndUniversityNotExistException e) {
		Map<String, String> result = new HashMap<>(2);
		result.put("uuid", e.getUuid());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		return result;
	}

	@ExceptionHandler(SenderMailException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handlerSendMailException(SenderMailException e) {
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "邮件发送失败");
		return result;
	}

}
