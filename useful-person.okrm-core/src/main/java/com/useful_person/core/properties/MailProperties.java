package com.useful_person.core.properties;

import lombok.Getter;
import lombok.Setter;

public class MailProperties {

//	@Getter
//	@Setter
//	private String host;
//
//	@Getter
//	@Setter
//	private String username;
//
//	@Getter
//	@Setter
//	private String password;
//
//	@Getter
//	@Setter
//	private String encoding = "UTF-8";
	
	@Getter
	@Setter
	private MailCodeProperties code;

	@Getter
	@Setter
	private MailVerificationProperties verification;

}
