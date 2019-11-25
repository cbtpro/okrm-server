package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class EventNotExistException extends RuntimeException {

	private static final long serialVersionUID = 8455452801922162239L;

	@Getter
	@Setter
	private String uuid;

	public EventNotExistException(String uuid) {
		super("task not exist");
		this.uuid = uuid;
	}

}
