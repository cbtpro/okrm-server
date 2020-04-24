package com.useful.person.core.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.annotation.HasAdminRole;
import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.Role;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.services.RoleService;
import com.useful.person.core.vo.ResponseData;
import com.useful.person.core.vo.RoleRequestVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ApiOperation("查询所有角色")
	@PostMapping("/roles")
	@HasAdminRole
	public ResponseData<List<Role>> queryRoles(@RequestBody RoleRequestVO role) {
		List<Role> roles = roleService.findAll(role);
		return new ResponseData<List<Role>>(ReturnCode.CORRECT.getCode(), null, roles);
	}

	@ApiOperation("新增角色")
	@PostMapping("/role")
	@HasAdminRole
	public ResponseData<Role> saveRole(Authentication authentication, @RequestBody Role role) {
		UserInfo user = (UserInfo) authentication.getPrincipal();
		Role newRole = roleService.saveRole(user.getUuid(), role);
		return new ResponseData<Role>(ReturnCode.CORRECT.getCode(), "添加成功！", newRole);
	}

	@ApiOperation("修改角色")
	@PutMapping("/role")
	@HasAdminRole
	public ResponseData<Role> updateRole(Authentication authentication, @RequestBody Role role) {
		UserInfo user = (UserInfo) authentication.getPrincipal();
		Role updatedRole = roleService.updateRole(user.getUuid(), role);
		return new ResponseData<Role>(ReturnCode.CORRECT.getCode(), "更新成功！", updatedRole);
	}

	@ApiOperation("删除角色")
	@DeleteMapping("/role")
	@HasAdminRole
	public ResponseData<String> deleteRole(Authentication authentication, @RequestBody Role role) {
		UserInfo user = (UserInfo) authentication.getPrincipal();
		roleService.delRole(user.getUuid(), role);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), "删除成功！", null);
	}
}
