package com.csipl.app.service;

import java.util.List;

import com.csipl.app.dto.EmployeeDTO;
import com.csipl.app.exception.ResourceNotFoundException;

public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployees();

	public EmployeeDTO getEmployeeById(Long employeeId) throws ResourceNotFoundException;

	public EmployeeDTO createEmployee(EmployeeDTO employee);

	public void deleteEmployee(Long employeeId) throws ResourceNotFoundException;

}
