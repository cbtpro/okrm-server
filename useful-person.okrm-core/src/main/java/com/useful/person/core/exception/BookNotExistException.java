/**
 * 
 */
package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class BookNotExistException extends RuntimeException {

	private static final long serialVersionUID = 3226425893537465108L;

	@Getter
	@Setter
	private String uuid;

	public BookNotExistException(String uuid) {
		super("book not exist");
		this.uuid = uuid;
	}
}
