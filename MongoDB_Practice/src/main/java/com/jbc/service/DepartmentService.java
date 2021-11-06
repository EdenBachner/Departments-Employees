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
import com.jbc.exception.department.DepartmentsEmptyException;
import com.jbc.exception.employee.EmployeeInvalidException;
import com.jbc.exception.employee.EmployeeNotFoundException;
import com.jbc.exception.employee.EmployeeNullException;
import com.jbc.model.Department;
import com.jbc.model.Employee;
import com.jbc.model.sequence.DepartmentSequence;
import com.jbc.repository.DepartmentRepository;
import com.jbc.repository.sequence.DepartmentSequenceRepository;
import com.jbc.service.ifc.DepartmentIfc;
import com.jbc.util.exception.attribute.DepartmentAttributes;
import com.jbc.util.model.validation.DepartmentValidations;

/**
 * {@code Service} that {@code implements} multiple {@code interface}s to ensure
 * the business logic of the system, related to the {@code Entity}s of the system.
 *
 * @author eden_bachner
 *
 */

@Service
public class DepartmentService implements DepartmentIfc, InitializingBean {

	/* attributes */
	@Autowired
	private DepartmentRepository departmentRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DepartmentSequenceRepository departmentSequenceRepo;
	private long departmentSequence; /* value will come from the data-base */
	@Autowired
	private EmployeeService employeeServ;


	@Override
	public Department createDepartment(Department department)
			throws DepartmentAlreadyExistsException, DepartmentNullException, DepartmentInvalidException {
		checkDepartment(department);
		try {
			getDepartment(department.getId());/* creating department if department not found */
			throw new DepartmentAlreadyExistsException(DepartmentAttributes.ID, department.getId());
		} catch (DepartmentNotFoundException e) {
			department.setId(departmentSequence);
			departmentSequence++;
			mongoTemplate.save(new DepartmentSequence(departmentSequence));
			return mongoTemplate.insert(department);
		}
	}

	@Override
	public Department updateDepartment(long departmentId, Department department) throws DepartmentNotFoundException,
			DepartmentNullException, DepartmentInvalidException, DepartmentAlreadyExistsException {
		checkDepartment(department);
		department.setEmployeesIds(/* keeping the department's employees */
				getDepartment(departmentId).getEmployeesIds());/* throwing exception if department not found */
		return mongoTemplate.save(department);
	}

	@Override
	public void deleteDepartment(long id) throws DepartmentNotFoundException {
		Department deletedDepartment = getDepartment(id);/* throwing exception if department not found */
		for (long employeeId : deletedDepartment.getEmployeesIds()) {
			try {
				employeeServ.updateEmployeeDepartment(employeeId, "");
			} catch (EmployeeNotFoundException e) {
				/* nothing to remove if employee is not found */
			}
		}
		departmentRepo.deleteById(id);
	}

	@Override
	public List<Department> getDepartments() throws DepartmentsEmptyException {
		List<Department> departments = departmentRepo.findAll();
		if (departments.isEmpty())
			throw new DepartmentsEmptyException();
		return departments;
	}

	@Override
	public Department getDepartment(long id) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepo.findById(id);
		if (!department.isPresent())
			throw new DepartmentNotFoundException(DepartmentAttributes.ID, id);
		return department.get();
	}

	@Override
	public Department getDepartment(String name) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepo.findByNameIgnoreCase(name);
		if (!department.isPresent()) {
			throw new DepartmentNotFoundException(DepartmentAttributes.NAME, name);
		}
		return department.get();
	}

	@Override
	public Employee addEmployee(long departmentId, long employeeId) throws EmployeeNotFoundException,
			DepartmentNotFoundException, DepartmentNullException, DepartmentInvalidException,
			DepartmentAlreadyExistsException, EmployeeInvalidException, EmployeeNullException {
		Employee employee = employeeServ.getEmployee(employeeId);
		Department department = getDepartment(departmentId);
		/* removing department if the employee is in a different department */
		if (!employee.getDepartmentName().isEmpty()) {
			try {
				Department currentEmployeeDepartment = getDepartment(employee.getDepartmentName());
				currentEmployeeDepartment.getEmployeesIds().remove(employeeId);
				mongoTemplate.save(currentEmployeeDepartment);
			} catch (DepartmentNotFoundException e) {
				/* nothing to remove if department is not found */
			}
		}
		/* adding employee to department */
		department.getEmployeesIds().add(employeeId);
		mongoTemplate.save(department);
		/* adding department to employee if needed */
		if (!employee.getDepartmentName().equalsIgnoreCase(department.getName())) {
			employee.setDepartmentName(department.getName());
			return employeeServ.updateEmployeeDepartment(employeeId, department.getName());
		}
		return employee;
	}

	@Override
	public void removeEmployee(long departmentId, long employeeId) throws DepartmentNotFoundException {
		Department department = getDepartment(departmentId);
		department.getEmployeesIds().remove(employeeId);
		try {
			employeeServ.updateEmployeeDepartment(employeeId, "");
		} catch (EmployeeNotFoundException e) {
			/* nothing to remove if employee is not found */
		}
		mongoTemplate.save(department);
	}

	/**
	 * Helper method, checks if a {@code Department} {@code Entity} already exists in the system, is not null and
	 * have valid attributes.
	 * 
	 * @param department
	 * @throws DepartmentNullException
	 * @throws DepartmentInvalidException
	 * @throws DepartmentAlreadyExistsException
	 */
	private void checkDepartment(Department department)
			throws DepartmentNullException, DepartmentInvalidException, DepartmentAlreadyExistsException {
		if (department == null || department.getName() == null)
			throw new DepartmentNullException();
		if (department.getName().length() < DepartmentValidations.NAME_MIN.toInt())
			throw new DepartmentInvalidException(DepartmentAttributes.NAME);
		if (department.getCapacity() < DepartmentValidations.CAPACITY_MIN.toInt())
			throw new DepartmentInvalidException(DepartmentAttributes.CAPACITY);
		if (departmentRepo.findByIdNotAndNameIgnoreCase(department.getId(), department.getName()).isPresent())
			throw new DepartmentAlreadyExistsException(DepartmentAttributes.NAME, department.getName());
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (departmentSequenceRepo.findAll().isEmpty()) {
			departmentSequence = mongoTemplate.save(new DepartmentSequence(getDepartmentsNextId())).getSequence();
		} else
			departmentSequence = departmentSequenceRepo.findAll().get(0).getSequence();
	}

/* returns the next available departmentId*/
	private long getDepartmentsNextId() {
		long maxId = 0;
		for (Department department : departmentRepo.findAll()) {
			if (department.getId() > maxId)
				maxId = department.getId();
		}
		return maxId + 1;
	}

}
