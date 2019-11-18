package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

public class SenderMailException extends RuntimeException {

	private static final long serialVersionUID = -6020199092308059095L;

	@Getter
	@Setter
	private String message;

	public SenderMailException(String message) {
		super("send mail fail.");
		this.message = message;
	}

}
