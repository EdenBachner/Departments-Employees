package com.jbc.exception.department;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.exception.attribute.DepartmentAttributes;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.Department} {@code Entity} which attribute
 * not valid
 * @author eden_bachner
 *
 */
public final class DepartmentInvalidException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -1188147176818121021L;
	
	/*attributes*/
	private DepartmentAttributes attribute;

	/* constructor */
	public DepartmentInvalidException(DepartmentAttributes attribute) {
		errorCode = ExceptionErrorCodeUtil.DepartmentInvalidException.toString();
		this.attribute = attribute;
	}

	/* toString*/
	@Override
	public String toString() {
		return "The Department: " + attribute.toString() + " is not valid, please make sure the attribute is valid.";
	}

}
