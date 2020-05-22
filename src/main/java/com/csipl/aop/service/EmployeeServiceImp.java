package com.csipl.aop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csipl.aop.exception.ResourceNotFoundException;
import com.csipl.aop.model.Employee;
import com.csipl.aop.repository.EmployeeRepository;

/**
 * Employee Service
 * 
 * @author shubham yaduwanshi
 *
 */
@Service
public class EmployeeServiceImp implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		return employeeRepository.findById(employeeId);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		final Employee updatedEmployee = employeeRepository.save(employee);
		return updatedEmployee;
	}

	public Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}