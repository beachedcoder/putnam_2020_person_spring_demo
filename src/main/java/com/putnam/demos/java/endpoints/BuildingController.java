package com.putnam.demos.java.endpoints;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.boundary.ErrorMessage;
import com.putnam.demos.java.domain.dto.BuildingDto;
import com.putnam.demos.java.domain.dto.BuildingsDTO;
import com.putnam.demos.java.services.BuildingServices;
import com.putnam.demos.java.services.dto.BuildingDtoService;


@RestController()
@RequestMapping("api/v20")
public class BuildingController {
	private Logger log = LoggerFactory.getLogger(BuildingController.class);
	private BuildingServices buildSvc;
	private ConversionService mapper;
	private BuildingDtoService buildDtoSvc;
	
	public BuildingController(BuildingServices bSvc, @Qualifier("dtoMapper") ConversionService typConverter, BuildingDtoService bSvcs) {
		this.buildSvc = bSvc;
		this.mapper = typConverter;
		this.buildDtoSvc = bSvcs;
	}
	
	// fetch a building, all buildings, add building update building
	@GetMapping(value = "buildings", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllBuildings(){
		return new ResponseEntity<BuildingsDTO>(
				this.mapper.convert(this.buildSvc.getCurrentLeasedLocations(),BuildingsDTO.class)
				, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "building",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewLeaseHoldBuilding( @RequestBody Building acquiredLocale) {
		Optional<Building> persistedBuilding =  this.buildSvc.addNewLeasedLocation(acquiredLocale);
		if (!persistedBuilding.isPresent()) {
			return new ResponseEntity<ErrorMessage>(
					new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Building>(persistedBuilding.get(), HttpStatus.CREATED);
	}

	//TODO finish then during class
	@GetMapping(value = "buildingdto",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findLeaseHoldLocationByCommonName(@RequestParam(value = "name")String buildingName){
		return null;
	}
	
	@GetMapping(value="buildingdto/{assetid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findleaseHoldLocation(@PathVariable(value = "assetid") long id){
		log.info(String.format("buildingdto/%s called", id));
		if (id < 1) {
			return new ResponseEntity<ErrorMessage>( 
					new ErrorMessage( HttpStatus.NOT_ACCEPTABLE.value(),"must provide good asset location id for detail lookup"),
					HttpStatus.BAD_REQUEST);
		}
		Optional<BuildingDto> foundLocale = this.buildDtoSvc.fetchLocaleById(id);
		if (!foundLocale.isPresent()) {
			return new ResponseEntity<ErrorMessage>(
					new ErrorMessage(HttpStatus.NOT_FOUND.value(), String.format("Unable to find location asset with id:(%s), please verify your asset id", id))
					, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<BuildingDto>(foundLocale.get(), HttpStatus.FOUND);
	}
	
	
	@PutMapping(value = "buildingdto", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCurrentLeaseHoldBuilding(@Valid @RequestBody BuildingDto updateLocale){
		Optional<BuildingDto> updatedDto = this.buildDtoSvc.updateExistingLocale(updateLocale);
		//TODO add error logic
		return new ResponseEntity<BuildingDto>(updatedDto.get(), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value = "buildingdto",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewLeaseHoldBuildingDto(@Valid @RequestBody BuildingDto acquiredLocale, BindingResult validationResult) {
		
		
		Optional<BuildingDto> persistedBuilding = this.buildDtoSvc.addNewLeasedBuilding(acquiredLocale);
		
		if (!persistedBuilding.isPresent()) {
			return new ResponseEntity<ErrorMessage>(
					new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<BuildingDto>(persistedBuilding.get(), HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	

}
