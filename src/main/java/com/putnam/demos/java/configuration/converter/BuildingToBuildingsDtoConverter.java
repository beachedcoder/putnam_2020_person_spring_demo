package com.putnam.demos.java.configuration.converter;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingDto;
import com.putnam.demos.java.domain.dto.BuildingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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
