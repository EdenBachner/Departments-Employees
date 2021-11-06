package com.jbc.exception.employee;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} {@link com.jbc.model.Employee} {@code Entity}.
 * @author eden_bachner
 *
 */
public final class EmployeeNullException extends CustomException {

	/* Serial */
	private static final long serialVersionUID = -5948352126186853228L;

	/* Constructor */
	public EmployeeNullException() {
		errorCode = ExceptionErrorCodeUtil.EmployeeNullException.toString();
	}

	/* toString */
	@Override
	public String toString() {
		return "The entered Employee is, or contains null, please make sure you instantiate the employee correctly.";
	}

}
