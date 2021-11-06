package com.jbc.util.exception.attribute;

public enum DepartmentAttributes {

	ID, NAME, CAPACITY, EMPLOYEES_IDS;

	@Override
	public String toString() {
		switch (this) {
		case NAME:
			return "Name";
		case CAPACITY:
			return "Capacity";
		case EMPLOYEES_IDS:
			return "Employees ID's";
		case ID:
			return "ID";
		default:
			return super.toString();
		}
	}

}
