package com.useful_person.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.useful_person.domain.User;

@RestController
public class UserController {

	/**
	 * 查询用户名是否存在
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> query(@RequestParam(name = "username", required = true) String nickname) {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	@RequestMapping(value = "/user/{id:[0-9A-Za-z]{8}-[0-9A-Za-z]{4}-[0-9A-Za-z]{4}-[0-9A-Za-z]{4}-[0-9A-Za-z]{12}}", method = RequestMethod.GET)
	public User getUserInfo(@PathVariable(name = "id", required = true) String uuid) {
		User user = new User();
		user.setNickname("tom");
		return user;
	}
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
	public boolean logout(User user) {
		return true;
	}

	/**
	 * 注册新用户
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signUp(User user) {
		return null;
	}

	/**
	 * 注销用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public boolean cancellationUser(User user) {
		return false;
	}
}
