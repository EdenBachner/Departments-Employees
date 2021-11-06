package com.jbc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jbc.model.Employee;

/**
 * {@link com.jbc.model.Employee} {@code MongoRepository} that is used to manage
 * the Employees in the system.
 * @author eden_bachner
 *
 */
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}
