package com.cg.healthcaresystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.service.UserService;
import com.cg.healthcaresystem.service.UserServiceImpl;

class CenterTest {
	UserService userService;
	DiagnosticCenter diagnosticCenterA;
	DiagnosticCenter diagnosticCenterE;
	com.cg.healthcaresystem.dto.Test testA;
	com.cg.healthcaresystem.dto.Test testE;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		userService = new UserServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
		userService=null;
	}

	@Test
	void addCenter() {
		diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		assertNull(diagnosticCenterA.getCenterId());
		assertNotNull(diagnosticCenterE.getCenterId());
		
	}
	
	@Test
	void removeCenter() {
		diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		assertTrue(userService.removeCenter(diagnosticCenterE.getCenterId()));
	}

	@Test
	void failRemoveCenter() {
		diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		assertFalse(userService.removeCenter(diagnosticCenterA.getCenterId()));
	}
	
	@Test
	void addTest() throws UserDefinedException{
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		assertEquals(testE, testA);
	}
	
	@Test
	void removeTest() throws UserDefinedException {
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		assertTrue(userService.removeTest(diagnosticCenterE.getCenterId(), testE.getTestId()));
	}
	
	@Test
	void failRemoveTest() throws UserDefinedException{
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		assertThrows(UserDefinedException.class, ()->userService.removeTest(diagnosticCenterE.getCenterId(), testE.getTestId()));
	}
	
	

}

