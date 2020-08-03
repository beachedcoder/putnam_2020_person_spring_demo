package com.putnam.demos.java.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.demos.java.domain.Employee;
import com.putnam.demos.java.domain.SalaryEmployee;

@Repository
public interface SalaryEmployeeRepository extends CrudRepository<SalaryEmployee, Long> {
	
	Optional<SalaryEmployee> findEmployeeByFirstNameAndDateOfBirth(String firstName, LocalDate dateOfBirth);
	
}
