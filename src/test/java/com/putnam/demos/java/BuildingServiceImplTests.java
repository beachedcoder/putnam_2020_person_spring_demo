package com.putnam.demos.java;

import com.putnam.demos.java.repositories.BuildingRepository;
import com.putnam.demos.java.services.BuildingServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class BuildingServiceImplTests {

	private BuildingServicesImpl svcFixture;
	@BeforeEach
	void setUp() throws Exception {
		this.svcFixture = new BuildingServicesImpl(mock(BuildingRepository.class));
	}

	@AfterEach
	void tearDown() throws Exception {
		this.svcFixture = null;
	}

	@Test
	void testAddNewLeasedLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testFindExistingLocationString() {
		fail("Not yet implemented");
	}

	@Test
	void testFindExistingLocationLong() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrentLeasedLocations() {
		fail("Not yet implemented");
	}

}
