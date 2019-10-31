package com.useful.person.core.validator.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * @author peter
 *
 */
public class ValidatorCodeException extends AuthenticationException {

	private static final long serialVersionUID = -1125394705996443271L;

	public ValidatorCodeException(String msg) {
		super(msg);
	}

}
