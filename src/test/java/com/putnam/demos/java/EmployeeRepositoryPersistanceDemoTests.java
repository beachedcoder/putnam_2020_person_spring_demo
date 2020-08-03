package com.putnam.demos.java;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.putnam.demos.java.domain.Department;
import com.putnam.demos.java.domain.Employee;
import com.putnam.demos.java.repositories.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EmployeeRepositoryPersistanceDemoTests {
	
	@Autowired
	private EmployeeRepository repoRef;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void proveCrudRepositoryStoresEmployee() {
		
		Employee currentEmp = new Employee("Java", "Java", LocalDate.of(1999, 1, 1), UUID.randomUUID(),
				Department.SALES, 100, 200);
		currentEmp.setEmployeeId(UUID.randomUUID());
		
		assertNotNull(this.repoRef, "repository auto injection failed to provided data context");
		
		Employee rtnEmp = this.repoRef.save(currentEmp);
		
		assertNotNull(rtnEmp, "failed to get persistence state version of saved employee");
		
		
	}
	
	@Test
	void provedFindByIdCrudRepository() {
		Employee currentEmp = new Employee("Georgia", "Java", LocalDate.of(1999, 1, 1), UUID.randomUUID(),
				Department.SALES, 100, 200);
		currentEmp.setEmployeeId(UUID.randomUUID());
		
		assertNotNull(this.repoRef, "repository auto injection failed to provided data context");
		
		Employee rtnEmp = this.repoRef.save(currentEmp);
		assertNotNull(rtnEmp, "failed to get persistence state version of saved employee");
		
		Optional<Employee> foundEmp = this.repoRef.findById(rtnEmp.getId());
		assertTrue(foundEmp.isPresent(), "failed to find stored object");
		Employee foundActual = foundEmp.get();
		
		assertEquals(rtnEmp.getFirstName(), foundActual.getFirstName());
		
	}
	

}
