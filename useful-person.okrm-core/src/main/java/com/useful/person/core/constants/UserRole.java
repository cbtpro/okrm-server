package com.useful.person.core.constants;

import lombok.Getter;
import lombok.Setter;

public enum UserRole {


	NORMAL(0, "NORMAL"),
	ADMIN(1, "ADMIN");

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String name;

	private UserRole(int code, String name) {
		this.code = code;
		this.name = name;
	}
}
