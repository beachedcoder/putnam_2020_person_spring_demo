package com.putnam.demos.java.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
	
	private float hourlySalary;
	private float maximumHoursPerWeek;

	public PartTimeEmployee() {
		super();
	}
	
	public PartTimeEmployee(String firstName, String lastName, LocalDate dateOfBirth, UUID pid, float hourlySalary, float maximumHoursPerWeek) {
		super(firstName, lastName, dateOfBirth, pid);
		this.hourlySalary = hourlySalary;
		this.maximumHoursPerWeek = maximumHoursPerWeek;
	}

	public float getHourlySalary() {
		return hourlySalary;
	}

	public void setHourlySalary(float hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	public float getMaximumHoursPerWeek() {
		return maximumHoursPerWeek;
	}

	public void setMaximumHoursPerWeek(float maximumHoursPerWeek) {
		this.maximumHoursPerWeek = maximumHoursPerWeek;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hourlySalary, maximumHoursPerWeek);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PartTimeEmployee))
			return false;
		PartTimeEmployee other = (PartTimeEmployee) obj;
		return Float.floatToIntBits(hourlySalary) == Float.floatToIntBits(other.hourlySalary)
				&& Float.floatToIntBits(maximumHoursPerWeek) == Float.floatToIntBits(other.maximumHoursPerWeek);
	}

	@Override
	public String toString() {
		return String.format("PartTimeEmployee [hourlySalary=%s, maximumHoursPerWeek=%s]", hourlySalary,
				maximumHoursPerWeek);
	}


}
