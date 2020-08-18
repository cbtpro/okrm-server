/**
 * 
 */
package com.useful.person.core.authentication.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
public class EmailNotExistException extends RuntimeException {

	private static final long serialVersionUID = 6699762125919381820L;

	@Getter
	@Setter
	private String email;

	public EmailNotExistException(String email) {
		super("email not exist!");
		this.email = email;
	}
}
