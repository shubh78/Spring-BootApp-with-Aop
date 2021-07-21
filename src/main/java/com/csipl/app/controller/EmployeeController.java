package com.csipl.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csipl.app.common.AppStatusEnum;
import com.csipl.app.dto.EmployeeDTO;
import com.csipl.app.exception.ResourceNotFoundException;
import com.csipl.app.model.Employee;
import com.csipl.app.service.EmployeeService;

/**
 * Employee Controller for request handling
 * @author shubham yaduwanshi
 *
 */
@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/employees")
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok().body(employee);
	}

	/**
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping("/employees")
	public EmployeeDTO createEmployee(@Validated @RequestBody EmployeeDTO employee) {
		return employeeService.createEmployee(employee);
	}

	/**
	 * 
	 * @param employeeId
	 * @param employeeDetails
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Validated @RequestBody EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		employeeService.updateEmployee(employeeId, employeeDetails);
		return ResponseEntity.ok().body(null);
	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		employeeService.deleteEmployee(employeeId);
	}

	@GetMapping("/exception/{id}")
	public ResponseEntity<AppStatusEnum[]> getAdviceException(@PathVariable(value = "id") Long employeeId) {
		if (employeeId > 0)
			throw new NullPointerException();
		return ResponseEntity.ok().body(AppStatusEnum.values());
	}
}