package com.csipl.aop.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.csipl.aop.exception.ResourceNotFoundException;
import com.csipl.aop.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Optional<Employee> getEmployeeById(Long employeeId) throws ResourceNotFoundException;

	public Employee createEmployee(Employee employee);

	public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException;

	public Map<String, Boolean> deleteEmployee(Long employeeId)throws ResourceNotFoundException;

}
