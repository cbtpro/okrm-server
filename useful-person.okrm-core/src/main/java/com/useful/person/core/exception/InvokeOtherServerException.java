package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class InvokeOtherServerException extends RuntimeException {

    private static final long serialVersionUID = -6020199092308059095L;

    @Getter
    private String code = "invoke.other.server.exception";

    @Getter
    @Setter
    private Object body;

    public InvokeOtherServerException(String message, Object body) {
        super(message);
        this.body = body;
    }

}
