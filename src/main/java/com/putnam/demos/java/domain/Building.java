package com.putnam.demos.java.domain;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Building extends AbstractEntity {

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

	public Building(long id, String localeName, int totalFloorsLeased) {
		this(localeName, totalFloorsLeased);
		this.setId(Long.valueOf(id));
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
		return Objects.hash(getId(), localeName, totalFloorsLeased);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Building))
			return false;
		Building other = (Building) obj;
		return getId().equals(other.getId()) && Objects.equals(localeName, other.localeName)
				&& totalFloorsLeased == other.totalFloorsLeased;
	}


	@Override
	public String toString() {
		return String.format("Building [id=%s, localeName=%s, totalFloorsLeased=%s]", getId(), localeName,
				totalFloorsLeased);
	}
	

}
