package com.putnam.demos.java.services.dto;

import java.util.Optional;

import javax.validation.Valid;

import com.putnam.demos.java.domain.dto.BuildingDto;

public interface BuildingDtoService {

	Optional<BuildingDto> addNewLeasedBuilding(BuildingDto newLocale);

	Optional<BuildingDto> fetchLocaleById(long id);

	Optional<BuildingDto> updateExistingLocale(BuildingDto updateLocale);
}
