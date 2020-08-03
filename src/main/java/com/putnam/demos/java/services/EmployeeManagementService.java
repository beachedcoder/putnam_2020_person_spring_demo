package com.putnam.demos.java.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.putnam.demos.java.domain.Employee;

public interface EmployeeManagementService {
Employee createNewEmployee(Employee empRef);
Optional<Employee> getEmployeeByFirstNameAndDateOfBirth(String firstName, LocalDate dob);
List<Employee> getAllEmployees();
}
