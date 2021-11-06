package com.jbc.service.ifc;

import java.util.List;

import com.jbc.exception.department.DepartmentAlreadyExistsException;
import com.jbc.exception.department.DepartmentInvalidException;
import com.jbc.exception.department.DepartmentNotFoundException;
import com.jbc.exception.department.DepartmentNullException;
import com.jbc.exception.department.DepartmentsEmptyException;
import com.jbc.exception.employee.EmployeeInvalidException;
import com.jbc.exception.employee.EmployeeNotFoundException;
import com.jbc.exception.employee.EmployeeNullException;
import com.jbc.model.Department;
import com.jbc.model.Employee;

/**
 * 
 * 
 * @author eden_bachner
 * @see model#Department
 *
 */
public interface DepartmentIfc {

	/**
	 * Creates a {@link com.jbc.model.Department} in the system.
	 * 
	 * @param department
	 * @return the created {@code Department}
	 * @throws DepartmentAlreadyExistsException {@code Department} with the {@code name} or {@code id} already exists.
	 * @throws DepartmentNullException
	 * @throws DepartmentInvalidException
	 */
	public Department createDepartment(Department department)
			throws DepartmentAlreadyExistsException, DepartmentNullException, DepartmentInvalidException;

	/**
	 * Update a {@link com.jbc.model.Department} in the system.
	 * 
	 * @param departmentId
	 * @param department
	 * @return the updated {@code Department}
	 * @throws DepartmentNotFoundException
	 * @throws DepartmentNullException
	 * @throws DepartmentInvalidException
	 * @throws DepartmentAlreadyExistsException
	 */
	public Department updateDepartment(long departmentId, Department department) throws DepartmentNotFoundException,
			DepartmentNullException, DepartmentInvalidException, DepartmentAlreadyExistsException;

	/**
	 * Delete a {@link com.jbc.model.Department} from the system.
	 * 
	 * @param id
	 * @throws DepartmentNotFoundException
	 */
	public void deleteDepartment(long id) throws DepartmentNotFoundException;

	/**
	 * 
	 * @return the {@code List} of the {@link com.jbc.model.Department} in the system.
	 * @throws DepartmentsEmptyException
	 */
	public List<Department> getDepartments() throws DepartmentsEmptyException;

	/**
	 * Adds a new {@link com.jbc.model.Employee} in the system.
	 * 
	 * @param departmentId
	 * @param employeeId
	 * @return the added {@code Employee}.
	 * @throws EmployeeNotFoundException
	 * @throws DepartmentNotFoundException
	 * @throws DepartmentNullException
	 * @throws DepartmentInvalidException
	 * @throws DepartmentAlreadyExistsException
	 * @throws EmployeeInvalidException
	 * @throws EmployeeNullException
	 */
	public Employee addEmployee(long departmentId, long employeeId) throws EmployeeNotFoundException,
			DepartmentNotFoundException, DepartmentNullException, DepartmentInvalidException,
			DepartmentAlreadyExistsException, EmployeeInvalidException, EmployeeNullException;

	/**
	 * Remove an {@link com.jbc.model.Employee} from the department
	 * 
	 * @param departmentId
	 * @param employeeId
	 * @throws DepartmentNotFoundException
	 */
	public void removeEmployee(long departmentId, long employeeId) throws DepartmentNotFoundException;

	/**
	 * 
	 * @param id
	 * @return one {@link com.jbc.model.Department} by ID.
	 * @throws DepartmentNotFoundException
	 */
	public Department getDepartment(long id) throws DepartmentNotFoundException;

	/**
	 * 
	 * @param name
	 * @return one {@link com.jbc.model.Department} by name.
	 * @throws DepartmentNotFoundException
	 */
	public Department getDepartment(String name) throws DepartmentNotFoundException;

}
