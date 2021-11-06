package com.jbc.exception.employee;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.Employee} {@code Entity} which {@code id}
 * already exists in the system.
 * @author eden_bachner
 *
 */
public final class EmployeeAlreadyExistsException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 5805331628955864357L;

	/*attributes*/
	private long id;

	/* constructor */
	public EmployeeAlreadyExistsException(long id) {
		errorCode = ExceptionErrorCodeUtil.EmployeeAlreadyExistsException.toString();
		this.id = id;
	}

	/* toString*/
	@Override
	public String toString() {
		return "Employee with the id: " + id + ", already exists, please create an employee with a different id.";
	}

}
