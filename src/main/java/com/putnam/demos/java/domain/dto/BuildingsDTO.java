package com.putnam.demos.java.domain.dto;

import java.util.List;

import com.putnam.demos.java.domain.Building;

public class BuildingsDTO {
	private List<Building> leaseHoldings;

	public BuildingsDTO(List<Building> holdings) {
		this.leaseHoldings = holdings;
	}

	public List<Building> getLeaseHoldings() {
		return leaseHoldings;
	}

	public void setLeaseHoldings(List<Building> leaseHoldings) {
		this.leaseHoldings = leaseHoldings;
	}
	
	

}
