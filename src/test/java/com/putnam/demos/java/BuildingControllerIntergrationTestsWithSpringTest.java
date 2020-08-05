package com.putnam.demos.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingsDTO;
import com.putnam.demos.java.repositories.BuildingRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
@SpringBootTest
@AutoConfigureMockMvc
class BuildingControllerIntergrationTestsWithSpringTest {

	@Autowired
	private MockMvc context;
	
	@Autowired
	private BuildingRepository buildRepo;
	
	private ObjectMapper objMapper;

	private Collection<Building> currentBuildings;
	
	@BeforeEach
	void setUp() throws Exception {
		objMapper = new ObjectMapper();
		currentBuildings = (Collection<Building>) this.buildRepo.findAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.objMapper = null;
		this.currentBuildings = null;
	}

	@Test
	void testGetAllBuildings() throws Exception {
		MvcResult rspMsg =
		this.context.perform(get("/buildings")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andReturn();
		
		BuildingsDTO rtnDto = objMapper.readValue(
				rspMsg.getResponse().getContentAsString(),
				new TypeReference<BuildingsDTO>() {}
				);
		assertNotNull(rtnDto,"failed to parse return object to DTO");
		
		assertTrue(rtnDto.getLeaseHoldings().containsAll(currentBuildings));
		
	}

	@Test
	void testAddNewLeaseHoldBuilding() throws Exception {
		Building rqBuilding = new Building("Hanover Square", 1);
		
		MvcResult rspMsg =
		this.context.perform(
				post("/building").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.objMapper.writeValueAsString(rqBuilding))
				).andDo(print())
		.andReturn();
	
		Building rspBuilding = this.objMapper.readValue(
				rspMsg.getResponse().getContentAsString(),
				new TypeReference<Building>() {});
		
		assertTrue(rspBuilding.getId()>0);
		assertEquals(rqBuilding.getLocaleName(), rspBuilding.getLocaleName());
		assertEquals(rqBuilding.getTotalFloorsLeased(), rspBuilding.getTotalFloorsLeased());
	}
	
	@ParameterizedTest
	@NullSource
	@EmptySource
	@ValueSource(strings = {"       "})
	void addNewLeaseHoldBuildingWithBadNames(String badName) throws Exception {
		Building rqBuilding = new Building(badName, 1);
		
		
		this.context.perform(
				post("/building").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.objMapper.writeValueAsString(rqBuilding))
				)
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andReturn();
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -1, -42})
	void addNewLeaseHoldBuildingWithBadNames(int badLease) throws Exception {
		Building rqBuilding = new Building("Never Persisted", badLease);
		
		this.context.perform(
				post("/building").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.objMapper.writeValueAsString(rqBuilding))
				)
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andReturn();
	}
}









