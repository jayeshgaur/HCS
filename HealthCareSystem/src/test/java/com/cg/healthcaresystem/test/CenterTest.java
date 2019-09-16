/**
 * 
 */
package com.cg.healthcaresystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.service.UserService;
import com.cg.healthcaresystem.service.UserServiceImpl;

class CenterTest {
	UserService userService;
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
	void testAddCenter() {
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		assertEquals(diagnosticCenterE, diagnosticCenterA);
	}

}
