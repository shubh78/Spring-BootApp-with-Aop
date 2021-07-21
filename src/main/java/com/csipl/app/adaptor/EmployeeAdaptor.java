package com.csipl.app.adaptor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.csipl.app.common.Adaptor;
import com.csipl.app.dto.EmployeeDTO;
import com.csipl.app.model.Employee;

@Component
public class EmployeeAdaptor implements Adaptor<EmployeeDTO, Employee> {

	@Override
	public List<Employee> uiDtoToDatabaseModelList(List<EmployeeDTO> list) {
		return list.stream().map(item -> uiDtoToDatabaseModel(item)).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeDTO> databaseModelToUiDtoList(List<Employee> list) {
		return list.stream().map(item -> databaseModelToUiDto(item)).collect(Collectors.toList());
	}

	@Override
	public Employee uiDtoToDatabaseModel(EmployeeDTO dto) {
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmailId(dto.getEmailId());
		return employee;
	}

	@Override
	public EmployeeDTO databaseModelToUiDto(Employee dbobj) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(dbobj.getId());
		dto.setFirstName(dbobj.getFirstName());
		dto.setLastName(dbobj.getLastName());
		dto.setEmailId(dbobj.getEmailId());
		return dto;
	}

}
