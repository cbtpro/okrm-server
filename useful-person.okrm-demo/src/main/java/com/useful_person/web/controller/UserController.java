package com.useful_person.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful_person.core.authentication.domain.UserInfo;
import com.useful_person.core.authentication.exception.UserNotExistException;
import com.useful_person.core.authentication.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}
	/**
	 * 查询用户名是否存在
	 * 
	 * @param nickname
	 * @return users
	 */
	@GetMapping
	@JsonView(UserInfo.UserInfoSimpleView.class)
	public boolean query(@RequestParam(name = "username", required = true) String username) {
		return userService.isExistUsername(username);
	}

	/**
	 * 获取用户详情
	 * 
	 * @param uuid
	 * @return user
	 */
	@GetMapping("/{id:[0-9A-Za-z]{32}}")
	@JsonView(UserInfo.UserInfoDetailView.class)
	public UserInfo getUserInfo(@PathVariable(name = "id", required = true) String uuid) {
		UserInfo user = userService.findByUuid(uuid);
		if (user == null) {
			throw new UserNotExistException(uuid);
		}
		return user;
	}

}
