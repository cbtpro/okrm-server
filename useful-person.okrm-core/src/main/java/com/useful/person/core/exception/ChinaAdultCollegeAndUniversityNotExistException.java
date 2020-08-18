package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class ChinaAdultCollegeAndUniversityNotExistException extends RuntimeException {

	private static final long serialVersionUID = -5086907678458340504L;

	@Getter
	@Setter
	private String uuid;

	public ChinaAdultCollegeAndUniversityNotExistException(String uuid) {
		super("China Adult College And University not exist");
		this.uuid = uuid;
	}

}
