package com.putnam.demos.java.repositories;

import java.util.Optional;

import com.putnam.demos.java.domain.Building;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
	Optional<Building> findByLocaleName(String localeName);
	//TODO updateExistingViaJpa
}
