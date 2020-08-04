package com.putnam.demos.java.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.repositories.BuildingRepository;
@Service
public class BuildingServicesImpl implements BuildingServices {

	private final BuildingRepository buildRepo;
	
	public BuildingServicesImpl(BuildingRepository dbRef) {
		this.buildRepo = dbRef;
	}

	@Override
	public Optional<Building> addNewLeasedLocation(Building location) {
		if (location == null || location.getLocaleName() == null|| location.getLocaleName().isBlank() || location.getTotalFloorsLeased()<1) {
			return Optional.empty();
		}
		return Optional.of(this.buildRepo.save(location));
	}

	@Override
	public Optional<Building> findExistingLocation(String locationName) {
		if (locationName == null || locationName.isBlank()) {
			return Optional.empty();
		}
		return this.buildRepo.findByLocaleName(locationName);
	}

	@Override
	public Optional<Building> findExistingLocation(long id) {
		if(id>0) {
		return this.buildRepo.findById(id);
		}
		return Optional.empty();
	}

	@Override
	public List<Building> getCurrentLeasedLocations() {
		return (List<Building>) this.buildRepo.findAll();
	}

}
