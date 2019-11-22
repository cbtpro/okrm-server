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
public class DepartmentUniversityNotExistException extends RuntimeException {

	private static final long serialVersionUID = -8676331070778413373L;

	@Getter
	@Setter
	private String uuid;

	public DepartmentUniversityNotExistException(String uuid) {
		super("Department University not exist");
		this.uuid = uuid;
	}
}
