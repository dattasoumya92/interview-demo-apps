package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.helper.EmployeeHelper;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeHelper employeeHelper;
	
	// create emp - validate employee exists or not
	@PostMapping
	private ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		ResponseEntity<Employee> response = null;
		if(employeeHelper.isExistingEmployee(employee)) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			employee = employeeHelper.saveEmployee(employee);
			response = ResponseEntity.ok(employee);
		}
		
		return response;
	}
	
	// delete emp
	@DeleteMapping("/{empId}")
	private ResponseEntity<String> deleteEmploye(@PathVariable Long empId) {
		// TODO Auto-generated method stub
		ResponseEntity<String> response = null;
		employeeHelper.deleteEmployee(empId);
		response = ResponseEntity.ok().build();
		return response;
	}
	
	// get emp by name
	@GetMapping("/{empName}")
	private ResponseEntity<List<Employee>> getEmpByName(@PathVariable String empName) {
		// TODO Auto-generated method stub
		ResponseEntity<List<Employee>> response = null;
		if(!StringUtils.isEmpty(empName)) {
			List<Employee> result = employeeHelper.getEmployeeByName(empName);
			if(CollectionUtils.isEmpty(result)) {
				response = ResponseEntity.notFound().build();
			}else {
				response = ResponseEntity.ok(result);
			}
		} else {
			response = ResponseEntity.badRequest().build();
		}
		return response;
	}
	
	
}
