package com.putnam.demos.java;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.boundary.ErrorMessage;
import com.putnam.demos.java.domain.dto.BuildingsDto;
import com.putnam.demos.java.repositories.BuildingRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class BuildingRestContollerIntegrationTests {

	private List<Building> currentHoldings;
	
	@Autowired
	private BuildingRepository buildingDB;
	@BeforeEach
	void setUp() throws Exception {
		currentHoldings = (List<Building>) buildingDB.findAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		currentHoldings = null;
	}

	@Test
	void testGetAllBuildings() {
		BuildingsDto rtnValue =
		given().accept(MediaType.APPLICATION_JSON_VALUE)
		.when().get("/buildings")
		.then()
		.statusCode(HttpStatus.ACCEPTED.value())
		.and()
		.extract().as(BuildingsDto.class);
		
		assertTrue(rtnValue.getLeaseHoldings().size() > 0);
//		assertIterableEquals(currentHoldings, rtnValue.getLeaseHoldings());
		assertTrue(rtnValue.getLeaseHoldings().containsAll(currentHoldings));
	}

	@Test
	void addNewLeaseHoldBuildingTest() {
		Building nueLocale = new Building("Girshwin Tower", 38);
		
		Building createdBuilding = 
		given().accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(nueLocale)
		.log().all()
		.when().post("/building")
		.then().log().all()
		.statusCode(HttpStatus.CREATED.value())
		.and().extract().as(Building.class);
		
		assertTrue(createdBuilding.getId()>0);
		assertEquals(nueLocale.getLocaleName(), createdBuilding.getLocaleName());
		
		
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"      "})
	void addNewLeaseholdBuildingWithBadNameTest(String badName){
		Building nueLocale = new Building(badName, 38);
		
		ErrorMessage rspMsg = 
		given().accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(nueLocale)
		.log().all()
		.when().post("/building")
		.then().log().all()
		.statusCode(HttpStatus.BAD_REQUEST.value())
		.and().extract().as(ErrorMessage.class);
		
		assertTrue(rspMsg.getErrorCode()==HttpStatus.NOT_ACCEPTABLE.value());
		
		
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -1,-42})
	void addNewLeaseholdBuildingWithBadLeasedFloorValueTest(int badLeaseValue){
		Building nueLocale = new Building("Hanover Square", badLeaseValue);
		ErrorMessage rspMsg = 
		given().accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(nueLocale)
		.log().all()
		.when().post("/building")
		.then().log().all()
		.statusCode(HttpStatus.BAD_REQUEST.value())
		.and().extract().as(ErrorMessage.class);
		
		assertTrue(rspMsg.getErrorCode()==HttpStatus.NOT_ACCEPTABLE.value());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
