/**
 * 
 */
package com.useful.person.core.authentication.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class MobileExistException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7187764948538214523L;

    @Getter
    @Setter
    private String mobile;

    public MobileExistException(String mobile) {
        super("mobile already exist!");
        this.mobile = mobile;
    }
}
