package com.jbc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbc.controller.api.EmployeeApi;
import com.jbc.exception.CustomException;
import com.jbc.message.request.EmployeeRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController implements EmployeeApi {

	@Autowired
	private EmployeeService employeeServ;

	@Override
	public ResponseEntity<?> createEmployee(EmployeeRequest employee) {
		try {
			return ResponseEntity.ok(employeeServ.createEmployee(employee.toEmployee()));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateEmployee(EmployeeRequest employee) {
		try {
			return ResponseEntity.ok(employeeServ.updateEmployee(employee.getId(), employee.toEmployee()));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<?> updateEmployee(long employeeId, String departmentName) {
		try {
			return ResponseEntity.ok(employeeServ.updateEmployeeDepartment(employeeId, departmentName));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}


	@Override
	public ResponseEntity<?> deleteEmployee(long id) {
		try {
			employeeServ.deleteEmployee(id);
			return ResponseEntity.ok(new SuccessResponse("Employee deleted successfully"));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> getEmployees() {
		try {
			return ResponseEntity.ok(employeeServ.getEmployees());
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getEmployee(long id) {
		try {
			return ResponseEntity.ok(employeeServ.getEmployee(id));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}


}
