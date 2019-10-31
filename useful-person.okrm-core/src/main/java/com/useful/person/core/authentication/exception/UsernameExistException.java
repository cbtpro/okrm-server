package com.useful.person.core.authentication.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class UsernameExistException extends RuntimeException {

	private static final long serialVersionUID = 223134528173245335L;

	@Getter
	@Setter
	private String username;

	public UsernameExistException(String uuid) {
		super("username exist");
		this.username = uuid;
	}

}
