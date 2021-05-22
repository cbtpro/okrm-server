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
public class MobileNotRegisteredException extends RuntimeException {

    private static final long serialVersionUID = -2349287081667943994L;

    @Getter
    @Setter
    private String mobile;

    public MobileNotRegisteredException(String mobile) {
        super("Mobile not registered");
        this.mobile = mobile;
    }

}
