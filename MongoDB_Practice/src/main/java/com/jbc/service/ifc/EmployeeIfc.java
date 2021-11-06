package com.jbc.service.ifc;

import java.util.List;

import com.jbc.exception.employee.EmployeeAlreadyExistsException;
import com.jbc.exception.employee.EmployeeInvalidException;
import com.jbc.exception.employee.EmployeeNotFoundException;
import com.jbc.exception.employee.EmployeeNullException;
import com.jbc.exception.employee.EmployeesEmptyException;
import com.jbc.model.Employee;

/**
 * 
 * @author eden_bachner
 * @see model#Employee
 *
 */
public interface EmployeeIfc {

	/**
	 * Creates a {@link com.jbc.model.Employee} in the system.
	 * 
	 * @param employee
	 * @return the created {@code Employee}
	 * @throws EmployeesEmptyException
	 * @throws EmployeeInvalidException
	 * @throws EmployeeAlreadyExistsException
	 * @throws EmployeeNullException
	 */
	public Employee createEmployee(Employee employee)
			throws EmployeesEmptyException, EmployeeInvalidException, EmployeeAlreadyExistsException, EmployeeNullException;

	/**
	 * Update a {@link com.jbc.model.Employee} in the system
	 * 
	 * @param employeeId
	 * @param employee
	 * @return the updated {@code Employee}
	 * @throws EmployeeNotFoundException
	 * @throws EmployeeInvalidException
	 * @throws EmployeeNullException
	 */
	public Employee updateEmployee(long employeeId, Employee employee)
			throws EmployeeNotFoundException, EmployeeInvalidException, EmployeeNullException;
	
	/**
	 * Update {@link com.jbc.model.Employee} {@link com.jbc.model.Department} in the system
	 * 
	 * @param employeeId
	 * @param employeeDepartment
	 * @return the updated {@code Employee}
	 * @throws EmployeeNotFoundException
	 */
	public Employee updateEmployeeDepartment(long employeeId,String employeeDepartment) throws EmployeeNotFoundException;

	/**
	 * Delete a {@link com.jbc.model.Employee} from the system.
	 * 
	 * @param id
	 * @throws EmployeeNotFoundException
	 */
	public void deleteEmployee(long id) throws EmployeeNotFoundException;

	/**
	 * 
	 * @return the {@code List} of the {@link com.jbc.model.Employee} in the system.
	 * @throws EmployeesEmptyException
	 */
	public List<Employee> getEmployees() throws EmployeesEmptyException;

	/**
	 * 
	 * @param id
	 * @return one {@link com.jbc.model.Department} by ID.
	 * @throws EmployeeNotFoundException
	 */
	public Employee getEmployee(long id) throws EmployeeNotFoundException;
	


}
