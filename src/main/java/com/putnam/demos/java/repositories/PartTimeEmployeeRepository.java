package com.putnam.demos.java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.putnam.demos.java.domain.PartTimeEmployee;
@Repository
public interface PartTimeEmployeeRepository extends CrudRepository<PartTimeEmployee, Long> {

}
