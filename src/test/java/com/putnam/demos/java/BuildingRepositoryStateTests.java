package com.putnam.demos.java;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.putnam.demos.java.domain.Building;
import com.putnam.demos.java.repositories.BuildingRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BuildingRepositoryStateTests {
    Logger testLog = LoggerFactory.getLogger(BuildingRepositoryStateTests.class);

    @Autowired
    BuildingRepository repoRef;

    @Order(value = 0)
    @Test
    void verifyRepositoryObjectPresent(){
        assertNotNull(this.repoRef, "failed to create repository");
    }

    @Order(value = 1)
    @Test
    void auditIsNewStateOfEntityPriorToPeristencseWithPostStatus(){
        Building structure = new Building("Beverly Lodge", 1);
        testLog.info("created new building");
        assertTrue(structure.isNew(), "initialized object should be new");

        Building persistedRef = this.repoRef.save(structure);
        testLog.info("persisted entity");
        assertFalse(persistedRef.isNew(), "returned object state should be false, as it's now a managed entity in db");

        persistedRef.setTotalFloorsLeased(structure.getTotalFloorsLeased()+1);

        testLog.info("updated entity in db context");
        assertFalse(persistedRef.isNew(),"didn't merge with existing entity");
    }
    
}