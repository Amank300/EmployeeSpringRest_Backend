package com.ak.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.springrest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
