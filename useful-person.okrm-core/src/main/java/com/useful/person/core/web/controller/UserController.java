package com.useful.person.core.web.controller;

import java.util.HashMap;
import java.util.List;
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
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;
import com.useful.person.core.domain.UserInfo.UserInfoSimpleView;
import com.useful.person.core.properties.AppConstants;
import com.useful.person.core.vo.Address;
import com.useful.person.core.vo.ResponseData;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author peter
 *
 */
@RestController("userController")
@RequestMapping("/user")
public class UserController {

//	@Autowired
//	private BasicRedisOperation basicRedisOperation;

	@Autowired
	private IUserService userService;

	@GetMapping("/me")
	@JsonView({ UserInfoDetailView.class })
	public ResponseData<UserInfo> getCurrentUser(Authentication user) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		UserInfo userInfo = userService.findByUuid(currentUser.getUuid());
		ResponseData<UserInfo> responseData = new ResponseData<UserInfo>(ReturnCode.CORRECT.getCode(), "", userInfo);
		return responseData;
	}

	/**
	 * 查询用户名是否存在
	 * 
	 * @param nickname
	 * @return users
	 */
	@GetMapping
	@JsonView(UserInfoSimpleView.class)
	public boolean query(@RequestParam(name = "username", required = true) String username) {
		return userService.isExistUsername(username);
	}

	@GetMapping(value = "/profile/detail")
	@JsonView(UserInfoDetailView.class)
	public ResponseData<UserInfo> getUserDetail(Authentication user) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		UserInfo userInfo = userService.findByUuid(currentUser.getUuid());
		return new ResponseData<UserInfo>(ReturnCode.CORRECT.getCode(), "", userInfo);
	}

	/**
	 * 获取用户详情
	 * 
	 * @param uuid
	 * @return user
	 */
	@GetMapping(ControllerConstants.PATH_UUID_SUFFIX)
	@JsonView(UserInfoDetailView.class)
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
	public ResponseData<String> updateMobile(Authentication user,
			@RequestParam(name = "mobile", required = true) String mobile) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateMobileByUuid(currentUser.getUuid(), mobile);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), "手机号更新成功！", mobile);
	}

	@ApiOperation("更新用户邮箱")
	@PutMapping("/email")
	public ResponseData<String> updateEmail(Authentication user,
			@RequestParam(name = "email", required = true) String email) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateEmailByUuid(currentUser.getUuid(), email);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), "邮箱地址更新成功！", email);
	}

	@ApiOperation("更新用户生日")
	@PutMapping("/birthday")
	public void updateBirthday(Authentication user, @RequestBody UserInfo userInfo) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateBirthdayByUuid(currentUser.getUuid(), userInfo.getBirthday());
	}

	@ApiOperation("解绑旧手机号")
	@PostMapping("/mobile/unbindOldMobile")
	public Map<String, String> unbindOldMobile(Authentication user,
			@RequestParam(name = "mobile", required = true) String mobile) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.unbindOldMobile(currentUser.getUuid(), mobile);
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "手机号解绑成功！");
		return result;
	}

	@ApiOperation("解绑旧邮箱地址")
	@PostMapping("/email/unbindOldEmail")
	public Map<String, String> unbindOldEmail(Authentication user,
			@RequestParam(name = "email", required = true) String email) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.unbindOldEmail(currentUser.getUuid(), email);
		Map<String, String> result = new HashMap<>(1);
		result.put(AppConstants.DEFAULT_RETURN_MESSAGE, "Email解绑成功！");
		return result;
	}

	@ApiOperation("更新用户实名制信息")
	@PutMapping("/realname")
	public ResponseData<String> updateRealname(Authentication user,
			@RequestParam(name = "idcardname", required = true) String identityCardName,
			@RequestParam(name = "idcardno", required = true) String identityCardNo) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateRealname(currentUser.getUuid(), identityCardName, identityCardNo);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), "实名制成功！", "");
	}

	@ApiOperation("获取用户位置信息")
	@GetMapping("/address")
	public Address getUserAddress(Authentication user) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		return userService.getUserAddress(currentUser.getUuid());
	}

	@ApiOperation("更新用户位置信息")
	@PutMapping("/address")
	public void updateAddress(Authentication user, @RequestBody Address address) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateAddressByUuid(currentUser.getUuid(), address);
	}

	@ApiOperation("更新用户密码")
	@PutMapping("/passwd")
	public ResponseData<String> updateUserPassword(Authentication user,
			@RequestParam(name = "oldPassword", required = true) String oldPassword,
			@RequestParam(name = "password", required = true) String password) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		userService.updateUserPassword(currentUser.getUuid(), oldPassword, password);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), "密码修改成功！", null);
	}

	@ApiOperation("更新用户信息")
	@PutMapping
	public UserInfo updateUserInfo(Authentication user, @RequestBody(required = true) UserInfo userInfo) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		return userService.updateUserInfo(currentUser.getUuid(), userInfo.getAvatar(), userInfo.getUsername(),
				userInfo.getNickname(), userInfo.getProvince(), userInfo.getCity(), userInfo.getCounty(),
				userInfo.getBirthday());
	}

	@ApiOperation("获取附近用户的位置信息")
//	@JsonView(AddressView.class)
	@PostMapping("/nearby/address")
	public List<Address> queryUserNearbyUserAddress(Authentication user,
			@RequestParam(name = "longitude0", required = true) Double longitude0,
			@RequestParam(name = "latitude0", required = true) Double latitude0,
			@RequestParam(name = "longitude1", required = true) Double longitude1,
			@RequestParam(name = "latitude1", required = true) Double latitude1,
			@RequestParam(name = "longitude", required = true) Double longitude,
			@RequestParam(name = "latitude", required = true) Double latitude) {
		UserInfo currentUser = (UserInfo) user.getPrincipal();
		return userService.getUserNearbyUserAddress(currentUser.getUuid(), longitude0, latitude0, longitude1, latitude1,
				longitude, latitude);
	}
}
