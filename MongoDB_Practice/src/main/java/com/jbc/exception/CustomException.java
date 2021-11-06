package com.jbc.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * custom exceptions that are customly created for the system, all exceptions
 * throws can be printed by using the {@code toString} or the
 * {@code printStackTrace} methods.
 * @author eden_bachner
 *
 */

@Getter
@Setter
public abstract class CustomException extends Exception {

	/* serial */
	private static final long serialVersionUID = -5768709396919501503L;

	/*attributes*/
	protected String errorCode;

}
