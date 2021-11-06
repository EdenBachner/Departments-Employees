package com.jbc.exception.department;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions related a returned
 * {@code List} of {@link com.jbc.Department} which is empty.
 * @author eden_bachner
 *
 */
public class DepartmentsEmptyException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -960829004929513135L;

	/* constructor */
	public DepartmentsEmptyException() {
		errorCode = ExceptionErrorCodeUtil.DepartmentsIsEmptyException.toString();
	}

	/* toString */
	@Override
	public String toString() {
		return "There are no departments in the system.";
	}
	
}
