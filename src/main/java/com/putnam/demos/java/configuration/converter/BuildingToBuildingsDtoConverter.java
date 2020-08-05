package com.putnam.demos.java.configuration.converter;

import java.util.List;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingDto;
import com.putnam.demos.java.domain.dto.BuildingsDTO;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class BuildingToBuildingsDtoConverter implements Converter<List<Building>, BuildingsDTO> {


    private ConversionService mapper;

    public BuildingToBuildingsDtoConverter(ConversionService verter) {
        this.mapper = verter;
    }

    @Override
    public BuildingsDTO convert(List<Building> buildings) {
        BuildingsDTO rtn = new BuildingsDTO();
        buildings.forEach(b -> rtn.addLeaseHoldings(mapper.convert(b, BuildingDto.class)));
        return rtn;
    }
}
