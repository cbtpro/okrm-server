package com.useful.person.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class SenderMailException extends RuntimeException {

    private static final long serialVersionUID = -6020199092308059095L;

    @Getter
    @Setter
    private String msg;

    public SenderMailException(String message) {
        super("send mail fail");
        this.msg = message;
    }

}
