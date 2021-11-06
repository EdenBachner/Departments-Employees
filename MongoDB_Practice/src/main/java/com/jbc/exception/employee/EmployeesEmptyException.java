package com.jbc.exception.employee;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
/**
 * Exception {@code class} used to {@code throw} exceptions related a returned
 * {@code List} of {@link com.jbc.Employee} which is empty.
 * @author eden_bachner
 *
 */
public final class EmployeesEmptyException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 1941986965669864501L;

	/* constructor */
	public EmployeesEmptyException() {
		errorCode = ExceptionErrorCodeUtil.EmployeesIsEmptyException.toString();
	}

	/* toString */
	@Override
	public String toString() {
		return "There are no employees in the system.";
	}
	
}
