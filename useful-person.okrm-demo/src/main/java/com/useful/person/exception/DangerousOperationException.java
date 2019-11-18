/**
 * 
 */
package com.useful.person.exception;

/**
 * @author peter
 *
 */
public class DangerousOperationException extends RuntimeException {

	private static final long serialVersionUID = 1953165401002813118L;

	public DangerousOperationException() {
		super("Batch operations are not allowed");
	}
}
