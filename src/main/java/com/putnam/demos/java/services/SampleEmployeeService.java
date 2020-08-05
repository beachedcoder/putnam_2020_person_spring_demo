package com.putnam.demos.java.services;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.putnam.demos.java.domain.Department;
import com.putnam.demos.java.domain.SalaryEmployee;

@Service
public class SampleEmployeeService implements EmployeeService {

	private UUID svcId;
	
	public SampleEmployeeService() {
		super();
		this.svcId = UUID.randomUUID();
	}

	@Override
	public SalaryEmployee getEmployee() {
		SalaryEmployee newbieHire = new SalaryEmployee("Janet", "Java", LocalDate.of(1999, 1, 1), UUID.randomUUID(), Department.VENDOR,
				202, 101);
		newbieHire.setEmployeeId(this.svcId);
		return newbieHire;
	}

}
