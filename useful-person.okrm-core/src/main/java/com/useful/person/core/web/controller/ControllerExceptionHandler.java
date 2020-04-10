package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.useful.person.core.authentication.exception.EmailExistException;
import com.useful.person.core.authentication.exception.GeneralException;
import com.useful.person.core.authentication.exception.MobileExistException;
import com.useful.person.core.authentication.exception.MobileNotRegisteredException;
import com.useful.person.core.authentication.exception.UserNotExistException;
import com.useful.person.core.authentication.exception.UsernameExistException;
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.exception.ChinaAdultCollegeAndUniversityNotExistException;
import com.useful.person.core.exception.ChinaCollegeAndUniversityNotExistException;
import com.useful.person.core.exception.EventNotExistException;
import com.useful.person.core.exception.OSSException;
import com.useful.person.core.exception.ResourceNotFoundException;
import com.useful.person.core.exception.SenderMailException;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.validator.code.ValidatorCodeException;
import com.useful.person.core.vo.ResponseData;

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
	public Map<String, Object> handleUsernameExistException(UsernameExistException usernameExistException) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("username", usernameExistException.getUsername());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, usernameExistException.getMessage());
		result.put(AppConstants.DEFAULT_RETURN_CODE, ReturnCode.ERROR.getCode());
		return result;
	}

	@ExceptionHandler(MobileExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> handlerMobileExistException(MobileExistException mobileExistException) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("mobile", mobileExistException.getMobile());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, mobileExistException.getMessage());
		result.put(AppConstants.DEFAULT_RETURN_CODE, ReturnCode.ERROR.getCode());
		return result;
	}

	@ExceptionHandler(MobileNotRegisteredException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> handleMobileNotRegisteredException(MobileNotRegisteredException e) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("mobile", e.getMobile());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		result.put(AppConstants.DEFAULT_RETURN_CODE, ReturnCode.ERROR.getCode());
		return result;
	}

	@ExceptionHandler(EmailExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> handlerEmailExistException(EmailExistException e) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("email", e.getEmail());
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		result.put(AppConstants.DEFAULT_RETURN_CODE, ReturnCode.ERROR.getCode());
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

	@ExceptionHandler(OSSException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handlerOSSException(OSSException e) {
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		return result;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handlerResourceNotFoundException(ResourceNotFoundException e) {
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		return result;
	}

	/**
	 * 处理通用异常
	 * @param e
	 * @return Map<String, Object> 错误消息
	 */
	@ExceptionHandler(GeneralException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handlerGeneralException(GeneralException e) {
		Map<String, Object> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, e.getMessage());
		result.put(AppConstants.DEFAULT_RETURN_CODE, ReturnCode.ERROR.getCode());
		return result;
	}

	@ExceptionHandler(ValidatorCodeException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData<String> handlerValidatorCodeException(ValidatorCodeException e) {
		return new ResponseData<String>(ReturnCode.ERROR.getCode(), e.getMessage(), null);
	}
}
