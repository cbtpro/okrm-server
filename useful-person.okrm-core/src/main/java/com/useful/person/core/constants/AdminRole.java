package com.useful.person.core.constants;

import com.useful.person.core.domain.Role;
import com.useful.person.core.properties.SecurityConstants;

import lombok.Getter;

public class AdminRole extends Role {

	@Getter
	private String name = SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + UserRole.ADMIN.getName();
}
