package com.demo.suvidha.repo;

import org.springframework.data.repository.CrudRepository;

import com.demo.suvidha.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>
{

}
