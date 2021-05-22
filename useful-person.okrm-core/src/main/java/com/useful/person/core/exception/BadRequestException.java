package com.useful.person.core.exception;

import lombok.Getter;

/**
 * 
 * @author peter
 *
 */
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -6020199092308059095L;

    @Getter
    private String code = "bad.request.exception";

    public BadRequestException(String message) {
        super(message);
    }

}
