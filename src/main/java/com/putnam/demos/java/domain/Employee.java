package com.putnam.demos.java.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Employee extends Person {
	private UUID employeeId;
	private Department department;
	private int roomAssignment;
	private int buildingNumber;
	
	public Employee() {
		super();
		this.employeeId = UUID.randomUUID();
	}

	public Employee(String firstName, String lastName, LocalDate dateOfBirth, UUID pid, Department department, int roomAssignment, int buildingNumber) {
		super(firstName, lastName, dateOfBirth, pid);
		this.department = department;
		this.roomAssignment = roomAssignment;
		this.buildingNumber = buildingNumber;
	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getRoomAssignment() {
		return roomAssignment;
	}

	public void setRoomAssignment(int roomAssignment) {
		this.roomAssignment = roomAssignment;
	}

	public int getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(buildingNumber, department, employeeId, roomAssignment);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		return buildingNumber == other.buildingNumber && department == other.department
				&& Objects.equals(employeeId, other.employeeId) && roomAssignment == other.roomAssignment;
	}

	@Override
	public String toString() {
		return String.format("Employee [employeeId=%s, department=%s, roomAssignment=%s, buildingNumber=%s]",
				employeeId, department, roomAssignment, buildingNumber);
	}
	
	
	
	
	
	
}
