package com.putnam.demos.java.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.demos.java.configuration.ManagedProperitesBean;
import com.putnam.demos.java.domain.Employee;
import com.putnam.demos.java.factory.SampleEmployeeFactory;
import com.putnam.demos.java.services.EmployeeManagementService;
import com.putnam.demos.java.services.EmployeeService;

@RestController
//TODO add base mapping
public class EmployeeController {
	
	private final SampleEmployeeFactory empFactory;
	private final EmployeeService empSvc;
	private final EmployeeManagementService empSvcImpl;
	
	@Autowired
	@Qualifier("ftEmp")
	private ManagedProperitesBean ftEmpProps;
	
	
	public EmployeeController(SampleEmployeeFactory empFactory, EmployeeService svcRef, EmployeeManagementService empRepo) {
		this.empFactory = empFactory;
		this.empSvc = svcRef;
		this.empSvcImpl = empRepo;
	}
	
	@GetMapping("employees")
	public ResponseEntity<List<Employee>> getCurrentEmployees() {
		
		return new ResponseEntity<List<Employee>>(
				this.empSvcImpl.getAllEmployees()
				, HttpStatus.OK);
		
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
