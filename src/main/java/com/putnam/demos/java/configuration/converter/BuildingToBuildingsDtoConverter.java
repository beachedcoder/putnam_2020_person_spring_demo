package com.putnam.demos.java.configuration.converter;

import java.util.List;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingDto;
import com.putnam.demos.java.domain.dto.BuildingsDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

public class BuildingToBuildingsDtoConverter implements Converter<List<Building>, BuildingsDto> {


    private ConversionService mapper;

    public BuildingToBuildingsDtoConverter(ConversionService verter) {
        this.mapper = verter;
    }

    @Override
    public BuildingsDto convert(List<Building> buildings) {
        BuildingsDto rtn = new BuildingsDto();
        buildings.forEach(b -> rtn.addLeaseHoldings(mapper.convert(b, BuildingDto.class)));
        return rtn;
    }
}
