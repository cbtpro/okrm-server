package com.useful.person.core.vo;

import lombok.Getter;
import lombok.Setter;

public class UsersRequestVO {

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String nickname;

	@Getter
	@Setter
	private String mobile;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	private Long registerTimeFrom;

	@Getter
	@Setter
	private Long registerTimeTo;

	@Getter
	@Setter
	private Boolean enabled;

	@Getter
	@Setter
	private int size = 10;

	@Getter
	@Setter
	private int page = 1;

	@Getter
	@Setter
	private String sortOrder = "ascend";

	@Getter
	@Setter
	private String sortField = "username";

	@Getter
	@Setter
	private String[] roles;
}
