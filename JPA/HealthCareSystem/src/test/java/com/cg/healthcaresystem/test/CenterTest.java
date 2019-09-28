package com.cg.healthcaresystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

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

class CenterTest {
	static UserService userService;
	static DiagnosticCenter diagnosticCenterA;
	static DiagnosticCenter diagnosticCenterE;
	static com.cg.healthcaresystem.dto.Test testA;
	static com.cg.healthcaresystem.dto.Test testE;
	static User user;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {	
		userService = new UserServiceImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		userService=null;
	}

	@BeforeEach
	void setUp() throws Exception {
		diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
		diagnosticCenterE = userService.addCenter(diagnosticCenterA);
		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
		user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");

	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void addCenter() throws UserDefinedException {
		assertNotNull(diagnosticCenterE.getCenterId());
		
	}
	
	@Test
	void removeCenter() throws UserDefinedException {
		assertTrue(userService.removeCenter(diagnosticCenterE.getCenterId()));
	}

	@Test
	void failRemoveCenter() throws UserDefinedException {
		assertFalse(userService.removeCenter(diagnosticCenterA.getCenterId()));
	}
	
	@Test
	void addTest() throws UserDefinedException{
		assertNotNull(testE.getTestId());
	}
	
	@Test
	void removeTest() throws UserDefinedException {
		assertTrue(userService.removeTest(diagnosticCenterE.getCenterId(), testE.getTestId()));
	}
	
	@Test
	void failRemoveTest() throws UserDefinedException{
		assertThrows(UserDefinedException.class, ()->{userService.removeTest(diagnosticCenterE.getCenterId(), BigInteger.valueOf(10000));});
	}
	
	@Test
	void register() {
		assertNotNull(userService.register(user));
	}
	
	@Test 
	void newAppointment() throws UserDefinedException{
		userService.register(user);
		Appointment appointment = new Appointment();
		assertNotNull(userService.addAppointment(appointment, diagnosticCenterE.getCenterId(), testE.getTestId(), user.getUserId(), LocalDateTime.now().plusDays(1)));
	}
	
//	@Test 
//	void failNewAppointment() throws UserDefinedException{
//		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
//		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
//		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
//		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
//		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),BigInteger.valueOf(8888),LocalDateTime.now().plusDays(1));
//		assertNull(userService.addAppointment(appointment).getAppointmentId());
//	}
//	
//	@Test
//	void viewAppointment() throws UserDefinedException {
//		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
//		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
//		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
//		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
//		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
//		user.setUserId(userService.register(user));
//		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),user.getUserId(),LocalDateTime.now().plusDays(1));
//		assertNotNull(userService.addAppointment(appointment).getAppointmentId());
//		assertNotNull(userService.getAppointmentList(user.getUserId()));
//	}
//	
//	@Test
//	void failViewAppointment() throws UserDefinedException {
//		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
//		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
//		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
//		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
//		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
//		user.setUserId(userService.register(user));
//		assertEquals(userService.getAppointmentList(user.getUserId()).size(), 0);
//	}
//	
//	@Test
//	void approveAppointment() throws UserDefinedException {
//		DiagnosticCenter diagnosticCenterA = new DiagnosticCenter("Center Name", "Center Address", BigInteger.valueOf(1234567890));
//		DiagnosticCenter diagnosticCenterE = userService.addCenter(diagnosticCenterA);
//		testA = (com.cg.healthcaresystem.dto.Test) new com.cg.healthcaresystem.dto.Test("Junit Test");
//		testE = userService.addTest(diagnosticCenterE.getCenterId(), testA);
//		User user = new User("Jayesh", "Jayesh@07",BigInteger.valueOf(1234567890),"jay@c.c",22,"M");
//		user.setUserId(userService.register(user));
//		Appointment appointment = new Appointment(diagnosticCenterE.getCenterId(),testE.getTestId(),user.getUserId(),LocalDateTime.now().plusDays(1));
//		assertNotNull(userService.approveAppointment(appointment.getAppointmentId()));
//	}
//	

}

