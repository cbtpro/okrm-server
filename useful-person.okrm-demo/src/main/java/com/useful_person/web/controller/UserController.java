package com.useful_person.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful_person.domain.User;
import com.useful_person.exception.UserNotExistException;
import com.useful_person.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	/**
	 * 注册新用户
	 * 
	 * @param user
	 * @return user
	 */
	@PostMapping
	@JsonView(User.UserInfoDetailView.class)
	public User createUser(@Valid @RequestBody User user, BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				String message = fieldError.getField() + " " + error.getDefaultMessage();
				System.out.println(message);
			});
		}
		User newUser = User.builder().username(user.getUsername()).nickname(user.getNickname())
				.password(user.getPassword()).birthday(user.getBirthday()).build();
		return userService.save(newUser);
	}

	/**
	 * 查询用户名是否存在
	 * 
	 * @param nickname
	 * @return users
	 */
	@GetMapping
	@JsonView(User.UserInfoSimpleView.class)
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
	@JsonView(User.UserInfoDetailView.class)
	public User getUserInfo(@PathVariable(name = "id", required = true) String uuid) {
		User user = userService.findByUuid(uuid);
		if (user == null) {
			throw new UserNotExistException(uuid);
		}
		return user;
	}

	/**
	 * 注销用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public boolean cancellationUser(User user) {
		return false;
	}
}
