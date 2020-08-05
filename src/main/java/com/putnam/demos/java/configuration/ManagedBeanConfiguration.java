package com.putnam.demos.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
