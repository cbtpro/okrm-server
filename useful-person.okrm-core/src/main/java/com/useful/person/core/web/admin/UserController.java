package com.useful.person.core.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.annotation.HasAdminRole;
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.vo.ResponseData;

import io.swagger.annotations.ApiOperation;

@RestController("managerUsersController")
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/users")
	@ApiOperation("查询用户列表")
	@HasAdminRole
	public ResponseData<List<UserInfo>> queryUsers() {
		List<UserInfo> users = userInfoService.queryUsers();
		return new ResponseData<List<UserInfo>>(ReturnCode.CORRECT.getCode(), "查询成功！", users);
	}
}
