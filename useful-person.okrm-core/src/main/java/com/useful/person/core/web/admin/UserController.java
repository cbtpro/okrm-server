package com.useful.person.core.web.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.services.UserInfoService;
import com.useful.person.core.vo.ResponseData;
import com.useful.person.core.vo.UsersRequestVO;

import io.swagger.annotations.ApiOperation;

@PreAuthorize("hasRole('" + SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + "ADMIN')")
@RestController("managerUsersController")
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/users")
	@ApiOperation("查询用户列表")
	public ResponseData<Page<UserInfo>> queryUsers(@RequestBody UsersRequestVO request) {
		Sort sort = Sort.by(request.getSortOrder().equals("ascend") ? Direction.ASC : Direction.DESC, request.getSortField());
		Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
		Long startTime = request.getRegisterTimeFrom();
		Long endTime = request.getRegisterTimeTo();
		Page<UserInfo> users = userInfoService.queryUsersPage(pageable, request.getUsername(), request.getNickname(), request.getMobile(), request.getEmail(),
			startTime != null ? new Date(startTime) : null, endTime != null ? new Date(endTime) : null, request.getEnabled(), request.getRoles());
		return new ResponseData<Page<UserInfo>>(ReturnCode.CORRECT.getCode(), null, users);
	}

	@PostMapping("/admins")
	@ApiOperation("查询拥有管理权限的用户")
	public ResponseData<Page<UserInfo>> queryUsersHasAdmin(@RequestBody UsersRequestVO request) {
		Sort sort = Sort.by(request.getSortOrder().equals("ascend") ? Direction.ASC : Direction.DESC, request.getSortField());
		Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
		Page<UserInfo> users = userInfoService.queryUsersHasAdminPage(pageable);
		return new ResponseData<Page<UserInfo>>(ReturnCode.CORRECT.getCode(), null, users);
	}

	@PutMapping("/admins")
	@ApiOperation("批量将用户添加进管理员角色")
	public ResponseData<String> addUsersToAdmin(@RequestBody List<String> usernames) {
		String msg = userInfoService.addUsernamesToAdmin(usernames);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), msg, null);
	}

	@DeleteMapping("/admins")
	@ApiOperation("批量删除用户的管理员角色")
	public ResponseData<String> removeUsersFromAdmin(@RequestParam(required = true) String uuid) {
		String msg = userInfoService.removeUserFromAdmin(uuid);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), msg, null);
	}
}
