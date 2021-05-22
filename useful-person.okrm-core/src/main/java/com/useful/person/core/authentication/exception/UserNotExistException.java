package com.useful.person.core.authentication.exception;

/**
 * 
 * @author peter
 *
 */
public class UserNotExistException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2095840904024606757L;

    private String uuid;

    public UserNotExistException(String uuid) {
        super("user not exist");
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
