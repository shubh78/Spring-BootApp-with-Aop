package com.csipl.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csipl.app.adaptor.EmployeeAdaptor;
import com.csipl.app.dto.EmployeeDTO;
import com.csipl.app.exception.ResourceNotFoundException;
import com.csipl.app.model.Employee;
import com.csipl.app.repository.EmployeeRepository;

/**
 * Employee Service
 * 
 * @author shubham yaduwanshi
 *
 */
@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	private EmployeeAdaptor employeeAdaptor;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();
		return employeeAdaptor.databaseModelToUiDtoList(employeeList);
	}

	@Override
	public EmployeeDTO getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		Employee emp = employeeRepository.findById(employeeId).isPresent() == true ? employee.get() : null;
		return employeeAdaptor.databaseModelToUiDto(emp);
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
		return employeeAdaptor
				.databaseModelToUiDto(employeeRepository.save(employeeAdaptor.uiDtoToDatabaseModel(employeeDto)));
	}

	@Override
	public void updateEmployee(Long employeeId, EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for  employeeId :: " + employeeId));
	}

	@Override
	public void deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		try {
			employeeRepository.deleteById(employeeId);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Employee not found for  employeeId :: " + employeeId);
		}

	}
}