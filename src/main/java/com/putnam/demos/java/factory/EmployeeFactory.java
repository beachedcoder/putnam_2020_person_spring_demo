package com.putnam.demos.java.factory;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.putnam.demos.java.domain.Department;
import com.putnam.demos.java.domain.Employee;

@Component()
public class EmployeeFactory implements SampleEmployeeFactory {

	private UUID employeeFact;
	
	public EmployeeFactory() {
		super();
		this.employeeFact = UUID.randomUUID();
	}

	@Override
	public Employee getSampleEmployee() {
		
		Employee nueEmployee = new Employee("Georgia", "Java", LocalDate.of(1999, 7, 4), UUID.randomUUID(), Department.SALES,
				670, 959);
		nueEmployee.setEmployeeId(this.employeeFact);
		return nueEmployee;
	}

}
