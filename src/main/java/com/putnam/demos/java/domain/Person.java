package com.putnam.demos.java.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person extends AbstractEntity {
	
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private UUID pid;
	
	public Person() {
		super();
		this.pid = UUID.randomUUID();
	}

	public Person(String firstName, String lastName, LocalDate dateOfBirth, UUID pid) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.pid = pid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UUID getPid() {
		return pid;
	}

	public void setPid(UUID pid) {
		this.pid = pid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, firstName, getId(), lastName, pid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(firstName, other.firstName)
				&& getId().equals(other.getId()) && Objects.equals(lastName, other.lastName) && Objects.equals(pid, other.pid);
	}

	@Override
	public String toString() {
		return String.format("Person [id=%s, firstName=%s, lastName=%s, dateOfBirth=%s, pid=%s]", getId(), firstName,
				lastName, dateOfBirth, pid);
	}

}
