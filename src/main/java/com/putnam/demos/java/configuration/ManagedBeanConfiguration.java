package com.putnam.demos.java.configuration;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.factory.BuildingsData;
import com.putnam.demos.java.factory.EmployeeFactory;
import com.putnam.demos.java.factory.SampleEmployeeFactory;

@Configuration
public class ManagedBeanConfiguration {

	@Bean(name = "ftEmp")
	public ManagedProperitesBean getEmployees() {
		//TODO load from local properties
		return new ManagedProperitesBean();
	}
	@Bean(name = "ptEmp")
	public ManagedProperitesBean getPartTimeEmployeeConfiguration() {
		//TODO load from local properties
		return new ManagedProperitesBean();
	}
	
	@Bean(name="buildings")
	@Scope("prototype")
	public List<Building>getAllBuildings(){
		return BuildingsData.getAvailableBuildings();
	}
}
