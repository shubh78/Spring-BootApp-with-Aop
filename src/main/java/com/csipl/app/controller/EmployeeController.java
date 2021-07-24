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
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping("/save")
	public EmployeeDTO createEmployee(@Validated @RequestBody EmployeeDTO employee) {
		return employeeService.createEmployee(employee);
	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok().body(employee);
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}


	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		employeeService.deleteEmployee(employeeId);
	}
}