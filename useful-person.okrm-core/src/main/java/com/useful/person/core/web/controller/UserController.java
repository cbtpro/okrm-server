package com.useful.person.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	@JsonView(UserInfoDetailView.class)
	public Object getCurrentUser(Authentication user) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		return currentUser;
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

	@GetMapping(value = "/profile/detail")
	@JsonView(UserInfo.UserInfoDetailView.class)
	public UserInfo getUserDetail(Authentication user) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		return userService.findByUuid(currentUser.getUuid());
	}
	/**
	 * 获取用户详情
	 * 
	 * @param uuid
	 * @return user
	 */
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	@JsonView(UserInfo.UserInfoDetailView.class)
	public UserInfo getUserInfo(@PathVariable(name = "uuid", required = true) String uuid) {
		UserInfo user = userService.findByUuid(uuid);
		return user;
	}

	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
	public void updateUserInfo(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody UserInfo userInfo) {
//		return userService.updateUserInfo(userInfo);
		userService.updateNicknameByUuid(uuid, userInfo.getNickname());
	}

//	@PutMapping(ControllerConstants.PATH_UUID_SUFFIX)
//	public UserInfo updateNickname(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody UserInfo userInfo) {
//		return userService.updateNicknameByUuid(uuid, userInfo.getNickname());
//		
//	}
}
