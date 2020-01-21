package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfo.UserInfoMobileSignupView;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.utils.ShortID;

/**
 * 
 * @author peter
 *
 */
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
	public UserInfo signIn(UserInfo userInfo) {
		return userInfo;
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
		Map<String, Object> map = new HashMap<String, Object>(2);
		if (errors.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>(5);
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				errorMap.put(fieldError.getField(), error.getDefaultMessage());
			});
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			map.put(AppConstants.DEFAULT_RETURN_MESSAGE, errorMap);
			return map;
		}
		UserInfo newUser = userService.register(user);
		map.put(AppConstants.DEFAULT_RETURN_MESSAGE, "用户注册成功！");
		map.put("user", newUser);
		return map;
	}

	@JsonView(UserInfoMobileSignupView.class)
	@PostMapping(SecurityConstants.DEFAULT_SIGN_UP_MOBILE_URL)
	public Map<String, Object> createUserByMobile(UserInfo user) {
		user.setPassword(UUID.randomUUID().toString());
		String shortID = ShortID.getShortUuid();
		user.setUsername(shortID);
		user.setNickname(shortID);
		UserInfo newUser = userService.registerByMobile(user);
		Map<String, Object> map = new HashMap<>(2);
		map.put(AppConstants.DEFAULT_RETURN_MESSAGE, "用户注册成功！");
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
