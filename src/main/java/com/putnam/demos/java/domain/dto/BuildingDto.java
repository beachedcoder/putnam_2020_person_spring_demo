package com.putnam.demos.java.domain.dto;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class BuildingDto {
	
	@Min(value = 1, message = "minimum asset Id value must be one")
    private long assetId;
	
	@Pattern(regexp = "^[\\w\\s]{2,40}$", message = "name of building on map must be word characters not exceeding forty")
    private String mapName;
    
    @Min(value = 1, message = "buildings require one or more floors to be leased prior to adding as leased asset")
    private int leasedFloors;

    public BuildingDto() {
    }

    public BuildingDto(long id, String mapName, int leasedFloors) {
        this.assetId = id;
        this.mapName = mapName;
        this.leasedFloors = leasedFloors;
    }

    public long getAssetId() {
        return assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getLeasedFloors() {
        return leasedFloors;
    }

    public void setLeasedFloors(int leasedFloors) {
        this.leasedFloors = leasedFloors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuildingDto)) return false;
        BuildingDto that = (BuildingDto) o;
        return getAssetId() == that.getAssetId() &&
                getLeasedFloors() == that.getLeasedFloors() &&
                getMapName().equals(that.getMapName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetId(), getMapName(), getLeasedFloors());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BuildingDto.class.getSimpleName() + "[", "]")
                .add("assetId=" + assetId)
                .add("mapName='" + mapName + "'")
                .add("leasedFloors=" + leasedFloors)
                .toString();
    }
}
