package com.jbc.exception.department;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;

/**
 * Exception {@code class} used to {@code throw} exceptions
 * related to a {@code null} {@link com.jbc.model.Department} {@code Entity}.
 * @author eden_bachner
 *
 */
public final class DepartmentNullException extends CustomException {

	/* Serial */
	private static final long serialVersionUID = -5935761979130763757L;

	/* Constructor */
	public DepartmentNullException() {
		errorCode = ExceptionErrorCodeUtil.DepartmentNullException.toString();
	}

	/* toString */
	@Override
	public String toString() {
		return "The entered Department is, or contains null, please make sure you instantiate the department correctly.";
	}

}
