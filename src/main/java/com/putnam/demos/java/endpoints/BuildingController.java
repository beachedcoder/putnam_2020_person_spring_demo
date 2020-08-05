package com.putnam.demos.java.endpoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.boundary.ErrorMessage;
import com.putnam.demos.java.domain.dto.BuildingsDto;
import com.putnam.demos.java.services.BuildingServices;

@RestController
public class BuildingController {

	private BuildingServices buildSvc;
	private ConversionService mapper;
	
	public BuildingController(BuildingServices bSvc, @Qualifier("dtoMapper") ConversionService typConverter) {
		this.buildSvc = bSvc;
		this.mapper = typConverter;
	}
	
	// fetch a building, all buildings, add building update building
	@GetMapping(value = "buildings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllBuildings(){
		return new ResponseEntity<BuildingsDto>(
				this.mapper.convert(this.buildSvc.getCurrentLeasedLocations(), BuildingsDto.class)
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
