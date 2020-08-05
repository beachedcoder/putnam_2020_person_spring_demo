package com.putnam.demos.java.configuration.converter;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConverterConfiguration {

    @Bean("dtoMapper")
    public ConversionService getConverters(){
        DefaultConversionService vert = new DefaultConversionService();
        vert.addConverter(BuildingDto.class, Building.class,(dto)-> new Building(dto.getAssetId(),dto.getMapName(),dto.getLeasedFloors()));
        vert.addConverter(Building.class,BuildingDto.class,(building -> new BuildingDto(building.getId(),building.getLocaleName(),building.getTotalFloorsLeased())));
        vert.addConverter(new BuildingToBuildingsDtoConverter(vert));
        return vert;
    }
}
