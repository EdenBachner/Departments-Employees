package com.jbc.exception.employee;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.exception.attribute.EmployeeAttributes;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.Employee} {@code Entity} which attribute
 * not valid
 * @author eden_bachner
 *
 */
public final class EmployeeInvalidException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 5695598264058639536L;

	/*attributes*/
	EmployeeAttributes attribute;

	/* constructor */
	public EmployeeInvalidException(EmployeeAttributes attribute) {
		errorCode = ExceptionErrorCodeUtil.EmployeeInvalidException.toString();
		this.attribute = attribute;
	}

	/* toString*/
	@Override
	public String toString() {
		return "The Employee: " + attribute.toString() + " is not valid, please make sure the attribute is valid.";
	}

}
