package com.cg.healthcaresystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.service.UserService;
import com.cg.healthcaresystem.service.UserServiceImpl;
import com.mysql.cj.result.LocalDateTimeValueFactory;

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
		assertNotNull(testE.getTestId());
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
		assertThrows(UserDefinedException.class, ()->{userService.removeTest(diagnosticCenterE.getCenterId(), BigInteger.valueOf(10000));});
	}
	
	@Test
	void register() {
		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
		assertNotNull(userService.register(user));
	}
	
	@Test 
	void newAppointment() throws UserDefinedException{
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
		BigInteger userId = userService.register(user);
		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),userId,LocalDateTime.now().plusDays(1));
		assertNotNull(userService.addAppointment(appointment));
	}
	
	@Test 
	void failNewAppointment() throws UserDefinedException{
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),BigInteger.valueOf(8888),LocalDateTime.now().plusDays(1));
		assertNull(userService.addAppointment(appointment).getAppointmentId());
	}
	
	@Test
	void viewAppointment() throws UserDefinedException {
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
		user.setUserId(userService.register(user));
		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),user.getUserId(),LocalDateTime.now().plusDays(1));
		assertNotNull(userService.addAppointment(appointment).getAppointmentId());
		assertNotNull(userService.getAppointmentList(user.getUserId()));
	}
	
	@Test
	void failViewAppointment() throws UserDefinedException {
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
		user.setUserId(userService.register(user));
		assertEquals(userService.getAppointmentList(user.getUserId()).size(), 0);
	}
	
	@Test
	void approveAppointment() throws UserDefinedException {
		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
		user.setUserId(userService.register(user));
		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),user.getUserId(),LocalDateTime.now().plusDays(1));
		assertNotNull(userService.approveAppointment(appointment.getAppointmentId()));
	}
	

}

