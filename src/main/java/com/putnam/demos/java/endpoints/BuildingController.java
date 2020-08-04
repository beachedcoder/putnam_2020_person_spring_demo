package com.putnam.demos.java.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.boundary.ErrorMessage;
import com.putnam.demos.java.domain.dto.BuildingsDTO;
import com.putnam.demos.java.services.BuildingServices;

@RestController
public class BuildingController {

	private BuildingServices buildSvc;
	
	public BuildingController(BuildingServices bSvc) {
		this.buildSvc = bSvc;
	}
	
	// fetch a building, all buildings, add building update building
	@GetMapping(value = "buildings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllBuildings(){
		return new ResponseEntity<BuildingsDTO>( 
				new BuildingsDTO(this.buildSvc.getCurrentLeasedLocations())
				, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "building",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addNewLeaseHoldBuilding( @RequestBody Building acquiredLocale) {
		Optional<Building> persistedBuilding =  this.buildSvc.addNewLeasedLocation(acquiredLocale);
		if (!persistedBuilding.isPresent()) {
			return new ResponseEntity<ErrorMessage>(
					new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Building>(persistedBuilding.get(), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	

}
