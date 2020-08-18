package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class ChinaCollegeAndUniversityNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1626033649291312149L;

	@Getter
	@Setter
	private String uuid;

	public ChinaCollegeAndUniversityNotExistException(String uuid) {
		super("china Colleges And Universities not exist");
		this.uuid = uuid;
	}

}
