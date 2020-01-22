/**
 * 
 */
package com.useful.person.core.authentication.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class EmailExistException extends RuntimeException {

	private static final long serialVersionUID = -1291541932789265649L;

	@Getter
	@Setter
	private String email;

	public EmailExistException(String email) {
		super("email already exist");
		this.email = email;
	}
}
