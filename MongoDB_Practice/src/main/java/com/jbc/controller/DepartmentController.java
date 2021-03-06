package com.jbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbc.controller.api.DepartmentApi;
import com.jbc.exception.CustomException;
import com.jbc.message.request.DepartmentRequest;
import com.jbc.message.response.ExceptionResponse;
import com.jbc.message.response.SuccessResponse;
import com.jbc.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController implements DepartmentApi {
	
	@Autowired
	private DepartmentService departmentServ;

	@Override
	public ResponseEntity<?> createDepartment(DepartmentRequest department) {
		try {
			return ResponseEntity.ok(departmentServ.createDepartment(department.toDepartment()));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> updateDepartment(DepartmentRequest department) {
		try {
			return ResponseEntity.ok(departmentServ.updateDepartment(department.getId(), department.toDepartment()));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> deleteDepartment(long id) {
		try {
			departmentServ.deleteDepartment(id);
			return ResponseEntity.ok(new SuccessResponse("Department deleted successfully"));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getDepartments() {
		try {
			return ResponseEntity.ok(departmentServ.getDepartments());
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> AddEmployee(long departmentId, long employeeId) {
		try {
			return ResponseEntity.ok(departmentServ.addEmployee(departmentId, employeeId));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<?> removeEmployee(long departmentId, long employeeId) {
		try {
			departmentServ.removeEmployee(departmentId, employeeId);
			return ResponseEntity.ok(new SuccessResponse("Employee removed successfully"));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getDepartment(long id) {
		try {
			return ResponseEntity.ok(departmentServ.getDepartment(id));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<?> getDepartment(String name) {
		try {
			return ResponseEntity.ok(departmentServ.getDepartment(name));
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}



}
