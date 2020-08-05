package com.putnam.demos.java.repositories;

import java.time.LocalDate;
import java.util.Optional;

import com.putnam.demos.java.domain.SalaryEmployee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryEmployeeRepository extends CrudRepository<SalaryEmployee, Long> {
	
	Optional<SalaryEmployee> findByFirstNameAndDateOfBirth(String firstName, LocalDate dateOfBirth);
	
}
