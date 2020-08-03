package com.putnam.demos.java.endpoints;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.demos.java.domain.Department;
import com.putnam.demos.java.domain.Employee;
import com.putnam.demos.java.factory.SampleEmployeeFactory;
import com.putnam.demos.java.services.EmployeeService;

@RestController
//TODO add base mapping
public class EmployeeController {
	
	private final SampleEmployeeFactory empFactory;
	private final EmployeeService empSvc;
	
	
	public EmployeeController(SampleEmployeeFactory empFactory, EmployeeService svcRef) {
		this.empFactory = empFactory;
		this.empSvc = svcRef;
	}

	@GetMapping("empservice")
	public ResponseEntity<Employee> getServiceEmployee() {
		
		return new ResponseEntity<Employee>(
				this.empSvc.getEmployee()
				, HttpStatus.CREATED);
		
	}

	@GetMapping("empsample")
	public ResponseEntity<Employee> getSampleEmployee() {
		
		return new ResponseEntity<Employee>(
				this.empFactory.getSampleEmployee()
				, HttpStatus.CREATED);
		
	}
	

}
