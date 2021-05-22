package com.useful.person.core.exception;

import lombok.Getter;

/**
 * 
 * @author peter
 *
 */
public class InternalServerException extends RuntimeException {

    private static final long serialVersionUID = -6020199092308059095L;

    @Getter
    private String code = "internal.server.exception";

    public InternalServerException(String message) {
        super(message);
    }

}
