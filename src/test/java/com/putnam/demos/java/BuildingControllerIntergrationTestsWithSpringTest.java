package com.putnam.demos.java;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.domain.dto.BuildingsDTO;
import com.putnam.demos.java.repositories.BuildingRepository;
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
	void testAddNewLeaseHoldBuilding() {
		
	}

}
