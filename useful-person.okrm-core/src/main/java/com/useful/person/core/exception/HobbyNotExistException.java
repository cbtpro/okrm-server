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
public class HobbyNotExistException extends RuntimeException {

	private static final long serialVersionUID = 5457429340252724248L;

	@Getter
	@Setter
	private String uuid;

	public HobbyNotExistException(String uuid) {
		super("hobby not exist");
		this.uuid = uuid;
	}
}
