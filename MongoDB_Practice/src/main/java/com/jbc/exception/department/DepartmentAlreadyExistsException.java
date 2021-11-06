package com.jbc.exception.department;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.exception.attribute.DepartmentAttributes;

/**
 * Exception {@code class} used to {@code throw} exceptions related to creation
 * of a {@link com.jbc.model.Department} {@code Entity} which {@code id}
 * already exists in the system.
 * @author eden_bachner
 *
 */
public class DepartmentAlreadyExistsException extends CustomException {

	/* serial */
	private static final long serialVersionUID = -6999516401879961430L;
	
	/*attributes*/
	private DepartmentAttributes attribute;
	private String value;

	/* constructor */
	public DepartmentAlreadyExistsException(DepartmentAttributes attribute, long id) {
		constructorInit(attribute);
		value = String.valueOf(id);
	}
	
	public DepartmentAlreadyExistsException(DepartmentAttributes attribute, String name) {
		constructorInit(attribute);
		value = name;
	}

	private void constructorInit(DepartmentAttributes attribute) {
		errorCode = ExceptionErrorCodeUtil.DepartmentAlreadyExistsException.toString();
		this.attribute = attribute;
	}

	/* toString*/
	@Override
	public String toString() {
		return "Department with the " + attribute + ": " + value
				+ ", already exist exception, please enter a different " + attribute + ".";
	}

}
