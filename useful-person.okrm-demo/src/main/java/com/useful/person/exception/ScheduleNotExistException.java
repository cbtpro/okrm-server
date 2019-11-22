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
public class ScheduleNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1953165401002813118L;

	@Getter
	@Setter
	private String uuid;

	public ScheduleNotExistException(String uuid) {
		super("schedule not exist exist");
		this.uuid = uuid;
	}
}
