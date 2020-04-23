package com.useful.person.core.web.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseData<Page<UserInfo>> queryUsers(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String nickname,
			@RequestParam(required = false) String mobile,
			@RequestParam(required = false) String email,
			@RequestParam(name = "registerTimeFrom", required = false) Long startTime,
			@RequestParam(name = "registerTimeTo", required = false) Long endTime, Boolean enabled,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "username") String sord) {
		Sort sort = new Sort(Direction.ASC, sord);
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<UserInfo> users = userInfoService.queryUsersPage(pageable, username, nickname, mobile, email,
				startTime != null ? new Date(startTime) : null, endTime != null ? new Date(endTime) : null, enabled);
		return new ResponseData<Page<UserInfo>>(ReturnCode.CORRECT.getCode(), "查询成功！", users);
	}
}
