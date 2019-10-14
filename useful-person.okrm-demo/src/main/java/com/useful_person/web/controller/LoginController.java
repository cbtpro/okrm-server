package com.useful_person.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful_person.core.authentication.domain.UserInfo;
import com.useful_person.core.authentication.services.IUserService;
import com.useful_person.core.properties.OkrmConstants;
import com.useful_person.core.properties.SecurityConstants;

@RestController
public class LoginController {

	@Autowired
	private IUserService userService;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public UserInfo signIn(UserInfo user) {
		return user;
	}

	/**
	 * 用户登出
	 * 
	 * @param user
	 * @return boolean
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public boolean logout(UserInfo user) {
		return true;
	}

	/**
	 * 注册新用户
	 * 
	 * @param user
	 * @return user
	 */
	@JsonView(UserInfo.UserInfoDetailView.class)
	@PostMapping(SecurityConstants.DEFAULT_SIGN_UP_URL)
	public Map<String, Object> createUser(HttpServletRequest request, HttpServletResponse response, @Valid UserInfo user, BindingResult errors) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (errors.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>();
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				//  String message = fieldError.getField() + " " + error.getDefaultMessage();
				errorMap.put(fieldError.getField(), error.getDefaultMessage());
			});
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			map.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, errorMap);
			return map;
		}
		UserInfo newUser = userService.register(user);
		map.put(OkrmConstants.DEFAULT_RETURN_MESSAGE, "用户注册成功");
		map.put("user", newUser);
		return map;
	}

	/**
	 * 注销用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public boolean cancellationUser(UserInfo user) {
		return false;
	}

}
