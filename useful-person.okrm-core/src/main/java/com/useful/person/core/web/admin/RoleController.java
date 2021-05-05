package com.useful.person.core.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.constants.ReturnCode;
import com.useful.person.core.domain.Role;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.services.RoleService;
import com.useful.person.core.vo.ResponseData;
import com.useful.person.core.vo.RoleRequestVO;
import com.useful.person.core.web.controller.ControllerConstants;

import io.swagger.annotations.ApiOperation;

@PreAuthorize("hasRole('" + SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + "ADMIN')")
@RestController
@RequestMapping("/admin")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ApiOperation("查询角色")
	@GetMapping("/role"+ ControllerConstants.PATH_UUID_SUFFIX)
	public ResponseData<Role> queryRole(@PathVariable(name = "uuid", required = true) String uuid) {
		Role role = roleService.findByUuid(uuid);
		return new ResponseData<Role>(ReturnCode.CORRECT.getCode(), null, role);
	}
	@ApiOperation("查询所有角色")
	@PostMapping("/roles")
	public ResponseData<List<Role>> queryRoles(@RequestBody RoleRequestVO role) {
		List<Role> roles = roleService.findAll(role);
		return new ResponseData<List<Role>>(ReturnCode.CORRECT.getCode(), null, roles);
	}

	@ApiOperation("新增角色")
	@PostMapping("/role")
	public ResponseData<Role> saveRole(Authentication authentication, @RequestBody Role role) {
		UserInfo user = (UserInfo) authentication.getPrincipal();
		Role newRole = roleService.saveRole(user.getUuid(), role);
		return new ResponseData<Role>(ReturnCode.CORRECT.getCode(), "添加成功！", newRole);
	}

	@ApiOperation("修改角色")
	@PutMapping("/role")
	public ResponseData<Role> updateRole(Authentication authentication, @RequestBody Role role) {
		UserInfo user = (UserInfo) authentication.getPrincipal();
		Role updatedRole = roleService.updateRole(user.getUuid(), role);
		return new ResponseData<Role>(ReturnCode.CORRECT.getCode(), "更新成功！", updatedRole);
	}

	@ApiOperation("删除角色")
	@DeleteMapping("/role")
	public ResponseData<String> deleteRole(Authentication authentication, @RequestParam(value = "uuid", required = true) String roleUuid) {
		UserInfo user = (UserInfo) authentication.getPrincipal();
		roleService.delRole(user.getUuid(), roleUuid);
		return new ResponseData<String>(ReturnCode.CORRECT.getCode(), "删除成功！", null);
	}
}
