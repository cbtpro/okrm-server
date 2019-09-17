package com.useful_person.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.useful_person.domain.User;

@RestController
public class LoginController {

	/**
	 * 用户登录
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public User signIn(User user) {
		return user;
	}

	/**
	 * 用户登出
	 * @param user
	 * @return boolean
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public boolean logout(User user) {
		return true;
	}

}
