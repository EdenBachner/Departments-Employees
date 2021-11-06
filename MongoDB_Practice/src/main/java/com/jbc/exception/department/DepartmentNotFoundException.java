package com.jbc.exception.department;

import com.jbc.exception.CustomException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.exception.attribute.DepartmentAttributes;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * searched value of a {@link com.jbc.model.Department} {@code Entity}
 * which was not found.
 * 
 * @author eden_bachner
 *
 */
public class DepartmentNotFoundException extends CustomException {

	/* serial */
	private static final long serialVersionUID = 2702378366111544961L;

	/* attributes */
	private DepartmentAttributes attribute;
	private String value;

	/* constructor */
	public DepartmentNotFoundException(DepartmentAttributes attribute, long id) {
		constructorInit(attribute);
		value = String.valueOf(id);
	}

	public DepartmentNotFoundException(DepartmentAttributes attribute, String name) {
		constructorInit(attribute);
		value = name;
	}

	private void constructorInit(DepartmentAttributes attribute) {
		errorCode = ExceptionErrorCodeUtil.DepartmentNotFoundException.toString();
		this.attribute = attribute;
	}

	/* ToString */
	@Override
	public String toString() {
		return "Department with the " + attribute + ": " + value + ", was not found, please make sure that the "
				+ attribute + " is correct.";
	}

}
