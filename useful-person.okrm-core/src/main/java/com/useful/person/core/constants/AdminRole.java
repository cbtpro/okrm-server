package com.useful.person.core.constants;

import com.useful.person.core.domain.Role;
import com.useful.person.core.properties.SecurityConstants;

import lombok.Getter;

public class AdminRole extends Role {

	private static final long serialVersionUID = 6671742557866166364L;

	@Getter
	private String name = SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + UserRole.ADMIN.getName();
}
