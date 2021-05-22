package com.useful.person.core.constants;

import lombok.Getter;
import lombok.Setter;

public enum ErrorType {

    INTERNAL_SERVER_ERROR(1, "Oops! Server has gone away!");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    private ErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
