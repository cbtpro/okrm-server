/**
 * 
 */
package com.useful.person.core.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public enum ReturnCode {

    CORRECT(0, "correct"), ERROR(1, "error");

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String type;

    private ReturnCode(int code, String type) {
        this.code = code;
        this.type = type;
    }
}
