/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.useful.person.core.authentication.exception.GeneralException;
import com.useful.person.core.authentication.exception.UserNotExistException;
import com.useful.person.core.constants.UserAction;
import com.useful.person.core.domain.Role;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.repository.RoleRepository;
import com.useful.person.core.repository.UserInfoLogRepository;
import com.useful.person.core.repository.UserInfoRepository;
import com.useful.person.core.services.RoleService;
import com.useful.person.core.vo.RoleRequestVO;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author peter
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserInfoLogRepository userInfoLogRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	@Transactional
	public Role saveRole(String uuid, Role role) {
		UserInfo currentUser = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
		Role newRole = roleRepository.save(role);
		UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.ADD_ROLE).actionValue(role.getRolename()).build();
		userInfoLogRepository.save(userInfoLog);
		return newRole;
	}

	@Override
	@Transactional
	public Role updateRole(String uuid, Role role) {
		UserInfo currentUser = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
		Role newRole = roleRepository.save(role);
		UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.UPDATE_ROLE).actionValue(role.getUuid()).build();
		userInfoLogRepository.save(userInfoLog);
		return newRole;
	}

	@Override
	@Transactional
	public void delRole(String uuid, Role role) {
		UserInfo currentUser = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
		roleRepository.delete(role);
		UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.DEL_ROLE).oldValue(role.getUuid()).actionValue(null).build();
		userInfoLogRepository.save(userInfoLog);
	}

	@Override
	public Role findByUuid(String uuid) {
		return roleRepository.findById(uuid).orElseThrow(() -> new GeneralException(uuid, "角色uuid不存在！"));
	}

	@Override
	public List<Role> findAll(RoleRequestVO request) {
		String sortField = request.getSortField();
		Sort sort = new Sort("ascend".equalsIgnoreCase(request.getSortOrder()) ? Direction.ASC : Direction.DESC, StringUtils.isBlank(sortField) ? "uuid" : sortField);
		return roleRepository.findAll(sort);
	}
}
