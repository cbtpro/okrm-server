package com.useful.person.core.web.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.annotation.HasAdminRole;
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.vo.ResponseData;
import com.useful.person.core.vo.UsersRequestVO;

import io.swagger.annotations.ApiOperation;

@RestController("managerUsersController")
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/users")
	@ApiOperation("查询用户列表")
	@HasAdminRole
	public ResponseData<Page<UserInfo>> queryUsers(@RequestBody UsersRequestVO request) {
		Sort sort = new Sort(request.getSortOrder().equals("ascend") ? Direction.ASC : Direction.DESC, request.getSortField());
		Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
		Long startTime = request.getRegisterTimeFrom();
		Long endTime = request.getRegisterTimeTo();
		Page<UserInfo> users = userInfoService.queryUsersPage(pageable, request.getUsername(), request.getNickname(), request.getMobile(), request.getEmail(),
			startTime != null ? new Date(startTime) : null, endTime != null ? new Date(endTime) : null, request.getEnabled(), request.getRoles());
		return new ResponseData<Page<UserInfo>>(ReturnCode.CORRECT.getCode(), null, users);
	}
}
