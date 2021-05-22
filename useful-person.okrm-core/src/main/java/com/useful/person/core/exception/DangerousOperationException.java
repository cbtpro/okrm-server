/**
 * 
 */
package com.useful.person.core.exception;

/**
 * @author peter
 *
 */
public class DangerousOperationException extends RuntimeException {

    private static final long serialVersionUID = 1953165401002813118L;

    public DangerousOperationException() {
        super("batch operations are not allowed");
    }
}
