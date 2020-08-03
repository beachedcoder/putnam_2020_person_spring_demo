package com.putnam.demos.java.factory;

import java.util.Arrays;
import java.util.List;

import com.putnam.demos.java.domain.Building;

public class BuildingsData {

	public BuildingsData() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Building> getAvailableBuildings(){
		return Arrays.asList(
				new Building(),
				new Building()
				);
	}

}
