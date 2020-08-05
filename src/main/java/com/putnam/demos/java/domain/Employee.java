package com.putnam.demos.java.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Employee extends Person {
	private UUID employeeId;

	{
		this.employeeId = UUID.randomUUID();
	}
	
	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName, LocalDate dateOfBirth, UUID pid) {
		super(firstName, lastName, dateOfBirth, pid);
	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
	
}
