package com.jbc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jbc.model.Department;

/**
 * {@link com.jbc.model.Department} {@code MongoRepository} that is used to manage
 * the Departments in the system.
 * @author eden_bachner
 *
 */
public interface DepartmentRepository extends MongoRepository<Department, Long> {

	/* methods */
	public Optional<Department> findByIdNotAndNameIgnoreCase(long departmentId, String departmentName);

	public Optional<Department> findByNameIgnoreCase(String name);

}
