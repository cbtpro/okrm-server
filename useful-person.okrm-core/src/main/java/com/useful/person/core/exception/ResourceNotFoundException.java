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
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1247635482191185428L;

	@Getter
	@Setter
	public String uuid;

	@Getter
	@Setter
	public String msg;

	public ResourceNotFoundException(String uuid, String msg) {
		super("resource not exist");
		this.uuid = uuid;
		this.msg = msg;
	}
}
