package com.putnam.demos.java;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.repositories.BuildingRepository;
import com.putnam.demos.java.services.BuildingServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import java.util.Optional;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class BuildingServiceImplTests extends CommonTestSettings{
	private Logger log = LoggerFactory.getLogger(BuildingServiceImplTests.class);
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
	void addNewLeaseHoldLocationWithNullValueTest(){
		Optional<Building> rtnValue = this.svcFixture.addNewLeasedLocation(null);
		assertTrue(rtnValue.isEmpty());
	}

	@ParameterizedTest
	@NullSource
	@EmptySource
	void addNewLeaseHoldLocationWithBadBuildingName(String curValue){
		log.info("running %s with value [%s]", this.getClass().getEnclosingMethod().getName(),curValue);
		Building curAsset = new Building(curValue, 1);
		Optional<Building> rtnValue = this.svcFixture.addNewLeasedLocation(curAsset);
		assertTrue(rtnValue.isEmpty(),"Added new lease hold building with bad name");
	}

	@Test
	void testAddNewLeasedLocation() {
		assertNotNull(this.svcFixture);
	}

	@Test
	void testFindExistingLocationString() {
		
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
