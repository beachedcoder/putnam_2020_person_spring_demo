package com.putnam.demos.java.services.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingDto;
import com.putnam.demos.java.services.BuildingServices;
@Service
public class BuildingDtoServiceImpl implements BuildingDtoService {

	private final BuildingServices maintenance;
	private final ConversionService mapper;
	
	public BuildingDtoServiceImpl(BuildingServices buildSvcs, @Qualifier("dtoMapper") ConversionService converter) {
		this.maintenance = buildSvcs;
		this.mapper = converter;
	}

	@Override
	public Optional<BuildingDto> addNewLeasedBuilding(BuildingDto newLocale) {
		//TODO add validation exception plan
		Building buildingEntity = this.mapper.convert(newLocale, Building.class);
		Optional<Building> peristedBuilding = this.maintenance.addNewLeasedLocation(buildingEntity);
		if (peristedBuilding.isEmpty()) {
			return Optional.empty();			
		}
		return Optional.of(this.mapper.convert(peristedBuilding.get(),BuildingDto.class));
	}

	@Override
	public Optional<BuildingDto> fetchLocaleById(long id) {
		//TODO add exception plan for validation
		Optional<Building> foundIt = this.maintenance.findExistingLocation(id);
		if (foundIt.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(this.mapper.convert(foundIt.get(),BuildingDto.class ));
	}

}
