package com.useful.person.core.startup;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.useful.person.core.constants.UserRole;
import com.useful.person.core.domain.Role;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.repository.RoleRepository;
import com.useful.person.core.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
public class InitSuperUser implements CommandLineRunner {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 初始化超级用户
	public void initSuperUser() {
		UserInfo su = userInfoRepository.findByUsername(SecurityConstants.DEFAULT_SUPER_USER);

		if (su == null) {
			String password = UUID.randomUUID().toString();
			su = UserInfo.builder().username(SecurityConstants.DEFAULT_SUPER_USER).nickname("superuser")
					.password(passwordEncoder.encode(password)).build();
			su = userInfoRepository.save(su);
			log.info("超级用户初始化密码：" + password);
		}
		Role adminRole = roleRepository.findByRolename(UserRole.ADMIN.getName());
		if (adminRole == null) {
			adminRole = Role.builder().rolename(UserRole.ADMIN.getName()).description("超级管理员用户").build();
			roleRepository.save(adminRole);
		}
		if (!su.hasRole(adminRole)) {
			su.addRole(adminRole);
			userInfoRepository.save(su);
		}
		Role normalRole = roleRepository.findByRolename(UserRole.NORMAL.getName());
		if (normalRole == null) {
			normalRole = Role.builder().rolename(UserRole.NORMAL.getName()).description("普通用户").build();
			roleRepository.save(normalRole);
		}
		if (!su.hasRole(normalRole)) {
			su.addRole(normalRole);
			userInfoRepository.save(su);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		initSuperUser();
	}

}
