package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfo.UserInfoMobileSignupView;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.utils.ShortID;
import com.useful.person.core.vo.ResponseData;

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
	public ResponseData<UserInfo> createUser(HttpServletRequest request, HttpServletResponse response, @Valid UserInfo user, BindingResult errors) throws Exception {
		ResponseData<UserInfo> responseData;
		if (errors.hasErrors()) {
			StringBuilder content = new StringBuilder();
			errors.getAllErrors().stream().forEach(error -> {
				content.append(error.getDefaultMessage());
			});
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			responseData = new ResponseData<UserInfo>(ReturnCode.CORRECT.getCode(), content.toString(), null);
			return responseData;
		}
		UserInfo newUser = userService.register(user);
		responseData = new ResponseData<UserInfo>(ReturnCode.CORRECT.getCode(), "用户注册成功！", newUser);
		return responseData;
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
