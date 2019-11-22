/**
 * 
 */
package com.useful.person.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class ResourceNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1247635482191185428L;

	@Getter
	@Setter
	public String uuid;

	public ResourceNotExistException(String uuid) {
		super("resource not exist");
		this.uuid = uuid;
	}
}
