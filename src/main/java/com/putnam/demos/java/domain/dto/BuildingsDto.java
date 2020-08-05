package com.putnam.demos.java.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BuildingsDto {
	private List<BuildingDto> leaseHoldings;
	private LocalDateTime lastRetrieval;
	
	public BuildingsDto() {
		super();
		this.leaseHoldings = new ArrayList<>();
		this.lastRetrieval = LocalDateTime.now();
	}

	public void addLeaseHoldings(BuildingDto leaseHold){
		this.leaseHoldings.add(leaseHold);
	}

	public List<BuildingDto> getLeaseHoldings() {
		return leaseHoldings;
	}

	public void setLeaseHoldings(List<BuildingDto> leaseHoldings) {
		this.leaseHoldings = leaseHoldings;
	}

	public LocalDateTime getLastRetrieval() {
		return lastRetrieval;
	}

	public void setLastRetrieval(LocalDateTime lastRetrieval) {
		this.lastRetrieval = lastRetrieval;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BuildingsDto)) return false;
		BuildingsDto that = (BuildingsDto) o;
		return getLeaseHoldings().equals(that.getLeaseHoldings()) &&
				getLastRetrieval().equals(that.getLastRetrieval());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getLeaseHoldings(), getLastRetrieval());
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", BuildingsDto.class.getSimpleName() + "[", "]")
				.add("leaseHoldings=" + leaseHoldings)
				.add("lastRetrieval=" + lastRetrieval)
				.toString();
	}
}
