package com.putnam.demos.java.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ManagedBeanConfiguration {

	@Bean(name = "ftEmp")
	public ManagedProperitesBean getEmployees() {
		//TODO load from local properties
		return new ManagedProperitesBean();
	}
	@Bean(name = "ptEmp")
	@DependsOn("ftEmp")
	public ManagedProperitesBean getPartTimeEmployeeConfiguration(@Qualifier("ftEmp") ManagedProperitesBean dependentValues) {
		//TODO load from local properties
		return new ManagedProperitesBean();
	}

}
