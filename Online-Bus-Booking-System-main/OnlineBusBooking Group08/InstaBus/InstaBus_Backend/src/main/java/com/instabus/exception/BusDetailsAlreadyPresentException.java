/**
 * 
 */
package com.instabus.exception;

public class BusDetailsAlreadyPresentException extends RuntimeException {

	/**
	 * @param message
	 * @param cause
	 */
	public BusDetailsAlreadyPresentException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusDetailsAlreadyPresentException(String message) {
		super(message);
	}

}
