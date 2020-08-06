package com.putnam.demos.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingsDto;
import com.putnam.demos.java.repositories.BuildingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class BuildingControllerIntergrationTestsWithSpringTest extends CommonTestSettings {

	private Logger log = LoggerFactory.getLogger(BuildingControllerIntergrationTestsWithSpringTest.class);
	@Autowired
	private MockMvc context;
	
	@Autowired
	private BuildingRepository buildRepo;

	@Autowired
	@Qualifier("dtoMapper")
	private ConversionService mapper;
	
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
		this.context.perform(get(this.BASE_URL+"buildings")).andDo(print()).andExpect(status().is2xxSuccessful())
		.andReturn();
		
		BuildingsDto rtnDto = objMapper.readValue(
				rspMsg.getResponse().getContentAsString(),
				new TypeReference<BuildingsDto>() {}
				);
		assertNotNull(rtnDto,"failed to parse return object to DTO");
		BuildingsDto expectedRtn = this.mapper.convert(this.currentBuildings,BuildingsDto.class);

		assertTrue(rtnDto.getLeaseHoldings().containsAll(expectedRtn.getLeaseHoldings()));
		
	}

	@Test
	void testAddNewLeaseHoldBuilding() throws Exception {
		Building rqBuilding = new Building("Hanover Square", 1);
		
		MvcResult rspMsg =
		this.context.perform(
				post(this.BASE_URL+"building").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
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
				post(this.BASE_URL+"/building").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
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
				post(this.BASE_URL+"building").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.objMapper.writeValueAsString(rqBuilding))
				)
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andReturn();
	}
}









