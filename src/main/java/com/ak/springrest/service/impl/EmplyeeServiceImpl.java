package com.ak.springrest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ak.springrest.exception.ResourceNotFoundException;
import com.ak.springrest.model.Employee;
import com.ak.springrest.repository.EmployeeRepository;
import com.ak.springrest.service.EmployeeService;

@Service
public class EmplyeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	

	public EmplyeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
			return employeeRepository.save(employee);
		}


	@Override
	public List<Employee> getAllEmployees() {
			return employeeRepository.findAll();
		}

	@Override
	public Employee getEmployeeById(long id) {
		/*
		 * Optional<Employee> employee = employeeRepository.findById(id);
		 * if(employee.isPresent()) { return employee.get();
		 * 
		 * }else { throw new ResourceNotFoundException("Employee", "Id", id); }
		 * 
		 * 
		 */
	
		return employeeRepository.findById(id).orElseThrow(()->
		               new ResourceNotFoundException("Employee","Id",id));
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//we need to check whether the given employee does exist in our DB
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//saving existing employee changes in DB
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}


	@Override
	public void deleteEmployee(long id) {
		//we need to check whether the given employee does exist in our DB
		employeeRepository.findById(id).orElseThrow(()-> 
										new ResourceNotFoundException("Employee","Id",id)); 
		employeeRepository.deleteById(id);
		
	}
		

}
