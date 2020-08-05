package com.putnam.demos.java.services;

import java.util.List;
import java.util.Optional;

import com.putnam.demos.java.domain.Building;

public interface BuildingServices {

	Optional<Building> addNewLeasedLocation(Building location);
	Optional<Building> findExistingLocation(String locationName);
	Optional<Building> findExistingLocation(long id);
	List<Building> getCurrentLeasedLocations();
	Building modifyExistingLeaseHold(Building existingBuilding);
	
}
