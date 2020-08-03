package com.putnam.demos.java.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.putnam.demos.java.domain.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeManagementService {

	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Employee createNewEmployee(Employee empRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Employee> getEmployeeByFirstNameAndDateOfBirth(String firstName, LocalDate dob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}


}
