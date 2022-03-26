package com.example.demo.helper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.data.EmployeeRepository;
import com.example.demo.entity.Employee;

@Component
public class EmployeeHelper {

	@Autowired
	private EmployeeRepository employeeRepository;

	public boolean isExistingEmployee(Employee employee) {
		boolean retflag = false;
		if (null != employee) {
			Optional<Employee> result = employeeRepository.findById(employee.getEmpId());
			retflag = result.isPresent();
		}
		return retflag;
	}

	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee responseEmp = null;
		if (null != employee) {
			responseEmp = employeeRepository.save(employee);
		}
		return responseEmp;
	}
	
	public List<Employee>  getEmployeeByName(String empName) {
		// TODO Auto-generated method stub
		List<Employee> result = employeeRepository.findByFirstNameLike(empName);
		return result;
	}
	
	public void deleteEmployee(Long empId) {
		// TODO Auto-generated method stub
		if(null != empId) {
			Optional<Employee> res = employeeRepository.findById(empId);
			if(res.isPresent()) {
				employeeRepository.delete(res.get());
			}
		}
	}

}
