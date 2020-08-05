package com.putnam.demos.java.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.putnam.demos.java.domain.Employee;
import com.putnam.demos.java.repositories.SalaryEmployeeRepository;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeManagementService {

	private final SalaryEmployeeRepository empRepo;
	
	public EmployeeServiceImpl(SalaryEmployeeRepository repRef) {	
		this.empRepo = repRef;
	}

	@Override
	public Employee createNewEmployee(Employee empRef) {
		// TODO validate rules of employee object before storing in repository
		return null;
	}

	@Override
	public Optional<Employee> getEmployeeByFirstNameAndDateOfBirth(String firstName, LocalDate dob) {
		
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
