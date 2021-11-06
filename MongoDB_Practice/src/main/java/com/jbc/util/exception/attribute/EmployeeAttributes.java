package com.jbc.util.exception.attribute;

public enum EmployeeAttributes {

	ID, FIRST_NAME, LAST_NAME, PROFESSION, DEPARTMENT_NAME;

	@Override
	public String toString() {
		switch (this) {
		case FIRST_NAME:
			return "First Name";
		case LAST_NAME:
			return "Last Name";
		case PROFESSION:
			return "Profession";
		case DEPARTMENT_NAME:
			return "Department Name";
		case ID:
			return "ID";
		default:
			return super.toString();
		}
	}

}
