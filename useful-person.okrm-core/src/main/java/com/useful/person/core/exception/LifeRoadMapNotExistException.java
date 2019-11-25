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
public class LifeRoadMapNotExistException extends ResourceNotExistException {

	private static final long serialVersionUID = -5849995828918754305L;

	@Getter
	@Setter
	private String uuid;

	public LifeRoadMapNotExistException(String uuid) {
		super("life road map not exist");
	}
}
