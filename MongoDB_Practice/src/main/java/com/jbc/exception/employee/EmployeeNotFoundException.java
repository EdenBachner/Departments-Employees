package com.jbc.exception.employee;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * searched value of a {@link com.jbc.model.Employee} {@code Entity}
 * which was not found.
 * @author eden_bachner
 *
 */
public final class EmployeeNotFoundException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -6819083860962347396L;

	/* attributes */
	private long id;

	/* constructor */
	public EmployeeNotFoundException(long id) {
		errorCode = ExceptionErrorCodeUtil.EmployeeNotFoundException.toString();
		this.id = id;
	}

	/* ToString */
	@Override
	public String toString() {
		return "Employee with the ID: " + id + ", was not found, please make sure that the ID is correct.";
	}

}
