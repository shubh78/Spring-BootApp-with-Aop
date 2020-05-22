package com.csipl.aop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csipl.aop.model.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository< Employee, Long > {

}