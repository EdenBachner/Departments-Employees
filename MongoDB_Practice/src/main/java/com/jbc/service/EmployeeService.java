package com.jbc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.jbc.exception.department.DepartmentAlreadyExistsException;
import com.jbc.exception.department.DepartmentInvalidException;
import com.jbc.exception.department.DepartmentNotFoundException;
import com.jbc.exception.department.DepartmentNullException;
import com.jbc.exception.employee.EmployeeAlreadyExistsException;
import com.jbc.exception.employee.EmployeeInvalidException;
import com.jbc.exception.employee.EmployeeNotFoundException;
import com.jbc.exception.employee.EmployeeNullException;
import com.jbc.exception.employee.EmployeesEmptyException;
import com.jbc.model.Department;
import com.jbc.model.Employee;
import com.jbc.model.sequence.EmployeeSequence;
import com.jbc.repository.EmployeeRepository;
import com.jbc.repository.sequence.EmployeeSequenceRepository;
import com.jbc.service.ifc.EmployeeIfc;
import com.jbc.util.exception.attribute.EmployeeAttributes;
import com.jbc.util.model.validation.EmployeeValidations;


/**
 * {@code Service} that {@code implements} multiple {@code interface}s to ensure
 * the business logic of the system, related to the {@code Entity}s of the system.
 * 
 * @author eden_bachner
 *
 */
@Service
public class EmployeeService implements EmployeeIfc, InitializingBean {

	/* attributes */
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private EmployeeSequenceRepository employeeSequenceRepo;
	private long employeeSequence; /* value will come from the data-base */
	@Autowired
	private DepartmentService departmentServ;


	@Override
	public Employee createEmployee(Employee employee)
			throws EmployeeAlreadyExistsException, EmployeeInvalidException, EmployeeNullException {
		checkEmployee(employee);
		try {
			getEmployee(employee.getId());/* creating employee if employee not found */
			throw new EmployeeAlreadyExistsException(employee.getId());
		} catch (EmployeeNotFoundException e) {
			employee.setId(employeeSequence);
			employeeSequence++;
			mongoTemplate.save(new EmployeeSequence(employeeSequence));
			return mongoTemplate.insert(employee);
		}
	}

	@Override
	public Employee updateEmployee(long employeeId, Employee employee)
			throws EmployeeNotFoundException, EmployeeInvalidException, EmployeeNullException {
		checkEmployee(employee);
		employee.setDepartmentName( /* keeping the employee's department */
				getEmployee(employeeId).getDepartmentName()); /* throwing exception if employee not found */
		return mongoTemplate.save(employee);
	}

	@Override
	public Employee updateEmployeeDepartment(long employeeId, String employeeDepartment)
			throws EmployeeNotFoundException {
		Employee employee = getEmployee(employeeId);
		employee.setDepartmentName(employeeDepartment);
		if (!employeeDepartment.isEmpty()) { /* nothing to add if employee has no department */
			try {
				Department department = departmentServ.getDepartment(employeeDepartment);
				/* checking if the department contains the employee */
				if (!department.getEmployeesIds().contains(employeeId)) {
					departmentServ.addEmployee(department.getId(), employeeId);
					/* adding employee to the department */
				}
			} catch (EmployeeNotFoundException | DepartmentNotFoundException | DepartmentNullException
					| DepartmentInvalidException | DepartmentAlreadyExistsException | EmployeeInvalidException
					| EmployeeNullException e) {
				/* nothing to remove if department is not found */
			}
		}
		return mongoTemplate.save(employee);
	}

	@Override
	public void deleteEmployee(long id) throws EmployeeNotFoundException {
		Employee employee = getEmployee(id);/* throwing exception if employee not found */
		if (!employee.getDepartmentName().isEmpty()) {
			try {
				Department department = departmentServ.getDepartment(employee.getDepartmentName());
				departmentServ.removeEmployee(department.getId(), id);
			} catch (DepartmentNotFoundException  e) {
				/* nothing to remove if department is not found */
			}
		}
		employeeRepo.deleteById(id);
	}

	@Override
	public List<Employee> getEmployees() throws EmployeesEmptyException {
		List<Employee> employees = employeeRepo.findAll();
		if (employees.isEmpty())
			throw new EmployeesEmptyException();
		return employees;
	}

	@Override
	public Employee getEmployee(long id) throws EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepo.findById(id);
		if (!employee.isPresent())
			throw new EmployeeNotFoundException(id);
		return employee.get();
	}

	/**
	 *  Helper method, checks if a {@code Employee} {@code Entity} is not null and
	 * have valid attributes.
	 * 
	 * @param employee
	 * @throws EmployeeInvalidException
	 * @throws EmployeeNullException
	 */
	private void checkEmployee(Employee employee) throws EmployeeInvalidException, EmployeeNullException {
		if (employee == null || employee.getFirstName() == null || employee.getLastName() == null
				|| employee.getDepartmentName() == null || employee.getProfession() == null) {
			throw new EmployeeNullException();
		}
		if (employee.getFirstName().length() < EmployeeValidations.FIRST_NAME_MIN.toInt())
			throw new EmployeeInvalidException(EmployeeAttributes.FIRST_NAME);
		if (employee.getLastName().length() < EmployeeValidations.LAST_NAME_MIN.toInt())
			throw new EmployeeInvalidException(EmployeeAttributes.LAST_NAME);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (employeeSequenceRepo.findAll().isEmpty())
			employeeSequence = mongoTemplate.save(new EmployeeSequence(getEmployeesNextId())).getSequence();
		else
			employeeSequence = employeeSequenceRepo.findAll().get(0).getSequence();
	}

	/* returns the next available employeeId*/
	private long getEmployeesNextId() {
		long maxId = 0;
		for (Employee employee : employeeRepo.findAll()) {
			if (employee.getId() > maxId)
				maxId = employee.getId();
		}
		return maxId + 1;
	}

}
