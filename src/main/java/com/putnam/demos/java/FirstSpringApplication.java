package com.putnam.demos.java;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.Department;
import com.putnam.demos.java.domain.Employee;
import com.putnam.demos.java.domain.PartTimeEmployee;
import com.putnam.demos.java.domain.SalaryEmployee;
import com.putnam.demos.java.repositories.SalaryEmployeeRepository;
import com.putnam.demos.java.repositories.BuildingRepository;
import com.putnam.demos.java.repositories.PartTimeEmployeeRepository;

@SpringBootApplication
public class FirstSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringApplication.class, args);
	}

	
	//DEFAULT DATA INITIALIZATION
	@Bean
	public CommandLineRunner initEmployeData(SalaryEmployeeRepository empRepo) {
		return (arg) ->{
			empRepo.saveAll(Arrays.asList(
					new SalaryEmployee("John", "Connolly", LocalDate.of(1999, 12, 1), UUID.randomUUID(), Department.INSTALLATION, 101, 100),
					new SalaryEmployee("Paul", "Jureidini", LocalDate.of(1999, 11, 1), UUID.randomUUID(), Department.ACCOUNTING, 102, 100),
					new SalaryEmployee("Emma", "Nettleship", LocalDate.of(1999, 10, 1), UUID.randomUUID(), Department.REPAIR, 103, 100),
					new SalaryEmployee("Mackenzi", "McKenna", LocalDate.of(1999, 9, 1), UUID.randomUUID(), Department.SALES, 104, 100),
					new SalaryEmployee("Swathi", "Jaisankar", LocalDate.of(1999, 8, 1), UUID.randomUUID(), Department.SUPPORT, 105, 100),
					new SalaryEmployee("Ryan", "Luu", LocalDate.of(1999, 7, 1), UUID.randomUUID(), Department.VENDOR, 106, 100),
					new SalaryEmployee("Aliza", "Camacho", LocalDate.of(1999, 6, 1), UUID.randomUUID(), Department.ACCOUNTING, 107, 100),
					new SalaryEmployee("Kate", "Chamberlain", LocalDate.of(1999, 5, 1), UUID.randomUUID(), Department.REPAIR, 108, 100),
					new SalaryEmployee("William", "Buford", LocalDate.of(1999, 4, 1), UUID.randomUUID(), Department.SALES, 109, 100),
					new SalaryEmployee("Mke", "Prensky", LocalDate.of(1999, 3, 1), UUID.randomUUID(), Department.SUPPORT, 110, 100),
					new SalaryEmployee("Tracy", "Grillo", LocalDate.of(1999, 2, 1), UUID.randomUUID(), Department.VENDOR, 111, 100)
					));
		};
	}
	
	@Bean
	public CommandLineRunner initPartTimeEmployeeData(PartTimeEmployeeRepository ptEmployeeRepo) {
		return (arg ) -> {
			ptEmployeeRepo.saveAll(
					Arrays.asList(
							new PartTimeEmployee("John", "Connolly", LocalDate.of(1999, 12, 1), UUID.randomUUID(),99.99f,40.0f),
							new PartTimeEmployee("Paul", "Jureidini", LocalDate.of(1999, 11, 1), UUID.randomUUID(),99.99f,40.0f),
							new PartTimeEmployee("Emma", "Nettleship", LocalDate.of(1999, 10, 1), UUID.randomUUID(),99.99f,40.0f),
							new PartTimeEmployee("Mackenzi", "McKenna", LocalDate.of(1999, 9, 1), UUID.randomUUID(),99.99f,40.0f),
							new PartTimeEmployee("Swathi", "Jaisankar", LocalDate.of(1999, 8, 1), UUID.randomUUID(), 99.99f,40.0f),
							new PartTimeEmployee("Ryan", "Luu", LocalDate.of(1999, 7, 1), UUID.randomUUID(),99.99f,40.0f),
							new PartTimeEmployee("Aliza", "Camacho", LocalDate.of(1999, 6, 1), UUID.randomUUID(), 99.99f,40.0f),
							new PartTimeEmployee("Kate", "Chamberlain", LocalDate.of(1999, 5, 1), UUID.randomUUID(),99.99f,40.0f),
							new PartTimeEmployee("William", "Buford", LocalDate.of(1999, 4, 1), UUID.randomUUID(), 99.99f,40.0f),
							new PartTimeEmployee("Mke", "Prensky", LocalDate.of(1999, 3, 1), UUID.randomUUID(), 99.99f,40.0f),
							new PartTimeEmployee("Tracy", "Grillo", LocalDate.of(1999, 2, 1), UUID.randomUUID(), 99.99f,40.0f)
							
					));
		};
	}
	
	@Bean
	public CommandLineRunner initBuildings(BuildingRepository dbRepo) {
		return (args) -> {
			dbRepo.saveAll(
					Arrays.asList(
							new Building("Federal Plaza", 14),
							new Building("One Water", 32),
							new Building("Coco Exchange", 45)
							));
		};
	}
	
	
	
	
}




