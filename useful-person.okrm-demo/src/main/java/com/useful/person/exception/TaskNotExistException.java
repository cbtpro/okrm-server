package com.useful.person.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class TaskNotExistException extends RuntimeException {

	private static final long serialVersionUID = 8455452801922162239L;

	@Getter
	@Setter
	private String uuid;

	public TaskNotExistException(String uuid) {
		super("task not exist exist");
		this.uuid = uuid;
	}

}
