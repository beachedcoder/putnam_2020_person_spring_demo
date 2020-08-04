package com.putnam.demos.java.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Building {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String localeName;
	private int totalFloorsLeased;

	public Building() {
		super();
	}
	

	public Building(String localeName, int totalFloorsLeased) {
		this();
		this.localeName = localeName;
		this.totalFloorsLeased = totalFloorsLeased;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLocaleName() {
		return localeName;
	}


	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}


	public int getTotalFloorsLeased() {
		return totalFloorsLeased;
	}


	public void setTotalFloorsLeased(int totalFloorsLeased) {
		this.totalFloorsLeased = totalFloorsLeased;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, localeName, totalFloorsLeased);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Building))
			return false;
		Building other = (Building) obj;
		return id == other.id && Objects.equals(localeName, other.localeName)
				&& totalFloorsLeased == other.totalFloorsLeased;
	}


	@Override
	public String toString() {
		return String.format("Building [id=%s, localeName=%s, totalFloorsLeased=%s]", id, localeName,
				totalFloorsLeased);
	}
	

}
