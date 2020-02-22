package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.authentication.services.IUserService;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.redis.impl.BasicRedisOperation;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

//	@Autowired
//	private BasicRedisOperation basicRedisOperation;

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	@JsonView(UserInfoDetailView.class)
	public UserInfo getCurrentUser(Authentication user) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		return userService.findByUuid(currentUser.getUuid());
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
		UserInfo detail = userService.findByUuid(currentUser.getUuid());
		return detail;
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

	@ApiOperation("更新用户名称")
	@PutMapping("/username")
	public void updateUsername(Authentication user, @RequestBody UserInfo userInfo) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateUsernameByUuid(currentUser.getUuid(), userInfo.getUsername());
	}

	@ApiOperation("更新用户昵称")
	@PutMapping("/nickname")
	public void updateNickname(Authentication user, @RequestBody UserInfo userInfo) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateNicknameByUuid(currentUser.getUuid(), userInfo.getNickname());
	}

	@ApiOperation("更新用户手机")
	@PutMapping("/mobile")
	public void updateMobile(Authentication user, @RequestParam(name = "mobile", required = true) String mobile) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateMobileByUuid(currentUser.getUuid(), mobile);
	}

	@ApiOperation("更新用户邮箱")
	@PutMapping("/email")
	public void updateEmail(Authentication user, @RequestParam(name = "email", required = true) String email) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateEmailByUuid(currentUser.getUuid(), email);
	}

	@ApiOperation("更新用户生日")
	@PutMapping("/birthday")
	public void updateBirthday(Authentication user, @RequestBody UserInfo userInfo) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateBirthdayByUuid(currentUser.getUuid(), userInfo.getBirthday());
	}

	@ApiOperation("解绑 旧手机号")
	@PostMapping("/mobile/unbindOldMobile")
	public Map<String, String> unbindOldMobile(Authentication user, @RequestParam(name = "mobile", required = true) String mobile) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.unbindOldMobile(currentUser.getUuid(), mobile);
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "手机号解绑成功！");
		return result;
	}

	@ApiOperation("解绑旧邮箱地址")
	@PostMapping("/email/unbindOldEmail")
	public Map<String, String> unbindOldEmail(Authentication user, @RequestParam(name = "email", required = true) String email) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.unbindOldEmail(currentUser.getUuid(), email);
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "Email解绑成功！");
		return result;
	}
}
