package com.csipl.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csipl.app.model.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository< Employee, Long > {

}