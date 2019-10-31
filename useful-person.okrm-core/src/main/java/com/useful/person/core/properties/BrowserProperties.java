package com.useful.person.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class BrowserProperties {

	@Getter
	@Setter
	private String signinPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

	@Getter
	@Setter
	private String signupPage = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;

	@Getter
	@Setter
	private SigninType signinType = SigninType.JSON;

	@Getter
	@Setter
	private int rememberMeSeconds = 3600;

}
