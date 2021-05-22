/**
 * 
 */
package com.useful.person.core.authentication.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
public class UsernameNotExistException extends RuntimeException {

    private static final long serialVersionUID = 6205538664723614572L;

    @Getter
    @Setter
    private String username;

    public UsernameNotExistException(String username) {
        super("username.not.exist");
        this.username = username;
    }
}
