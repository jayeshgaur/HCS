package com.cg.healthcaresystemrest.controller;

/*
 * Author: Jayesh Gaur
 * Description: Controller class for all functionalities
 * Created on: October 14, 2019
 */
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.cg.healthcaresystemrest.dto.Appointment;
import com.cg.healthcaresystemrest.dto.AppointmentRequest;
import com.cg.healthcaresystemrest.dto.DiagnosticCenter;
import com.cg.healthcaresystemrest.dto.Test;
import com.cg.healthcaresystemrest.dto.User;
import com.cg.healthcaresystemrest.exceldownload.ExcelReportView;
import com.cg.healthcaresystemrest.exception.ValidationException;
import com.cg.healthcaresystemrest.service.UserServiceImpl;

@ComponentScan
@RestController
public class HCSController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	HttpSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(HCSController.class);
	
	
	/*
	 * Author: 		Jayesh Gaur
	 * Description: /adminpage and /userpage -> used for debugging JWT Authentication
	 * Created on: October 14, 2019
	 */
	@GetMapping(value = "/adminpage")
	public String adminPage() {
		logger.info("Returning admin page..");
		return "Admin Page..";
	}
	
	@GetMapping(value = "/userpage")
	public String userPage() {
		logger.info("Returning user page..");
		return "User Page..";
	}
	
	/*
	 * Author: 			Jayesh Gaur 
	 * Description: 	Returns a list of all centers
	 * Created on: 		October 9, 2019 
	 * Input: 			NULL
	 * Output: 			A list of all center objects
	 */ 
	@GetMapping(value="/getCenters")
	public ResponseEntity<List<DiagnosticCenter>> getCenters(){
		logger.info("Getting list of all centers in the system..");
		List<DiagnosticCenter> centerList = userService.getCenterList();
		if(centerList.size() == 0) {
			logger.info("No centers present in the system. Returning NO_CONTENT");
			return new ResponseEntity<List<DiagnosticCenter>>(HttpStatus.NO_CONTENT);
		}else {
			logger.info("Returning center list..");
			return new ResponseEntity<List<DiagnosticCenter>>(centerList,HttpStatus.OK);
		}
		}
	
	/*
	 * Author: 			Jayesh Gaur 
	 * Description: 	Returns a list of all tests in the center corresponding to the center Id
	 * Created on: 		October 9, 2019 
	 * Input: 			Center Id of the center you want to retrieve tests of
	 * Output: 			A list of all test objects in the center object corresponding to the
	 * 						center Id entered
	 */ 
	@GetMapping(value="/getTests")
	public ResponseEntity<List<Test>> getTests(@RequestParam("centerId") String stringCenterId){
		BigInteger centerId=null;
		List<Test> testList = null;
		try {
			logger.info("Validating center id entered as parameter..");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			logger.info("Center Id validated... Getting test List");
			testList = userService.getListOfTests(centerId);
			if (testList.size() > 0) {
				System.out.println(testList);
				logger.info("Returning test list in the center..");
				return new ResponseEntity<List<Test>>(testList, HttpStatus.OK);
			} else {
				logger.info("Test list is empty.");
				return new ResponseEntity<List<Test>>(HttpStatus.NO_CONTENT);
			}
		} catch (ValidationException exception) {
			logger.error("Caught Validation Exception in /getTests Controller... ");
			return new ResponseEntity<List<Test>>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	/*
	 * Author: 			Jayesh Gaur 
	 * Description: 	Creates an appointment for the user. A
	 * Created on: 		October 9, 2019 
	 * Input: 			Center Id, Test Id and DateAndTime in the form of String 
	 * Output: 			Appointment object of the newly created appointment
	 */ 
	
	@PostMapping(value="/addAppointment")
	public ResponseEntity<?> addAppointment(@RequestBody AppointmentRequest appointmentRequest) {
		BigInteger centerId = null;
		BigInteger testId = null;
		BigInteger userId = null;
		LocalDateTime dateAndTime = null;
		try{
			//Getting the center object
			logger.info("Validating center Id entered for appointment..");
			centerId = userService.validateCenterId(appointmentRequest.getCenterId(), userService.getCenterList());
			logger.info("Getting the center corresponding to the center Id");
			DiagnosticCenter center = userService.findCenter(centerId);
			
			//Getting the test object
			logger.info("Validating test Id entered for appointment..");
			testId = userService.validateTestId(appointmentRequest.getTestId(), userService.getListOfTests(centerId));
			logger.info("Getting the test corresponding to the test Id");
			Test test = userService.findTest(testId);
			
			//getting the user object
			logger.info("Validating user Id entered for appointment");
			userId = userService.validateUserId(appointmentRequest.getUserId());
			logger.info("Getting the user corresponding to the user Id");
			User user = userService.findUser(userId);
			
			//Validating date and time
			logger.info("Validating date and time..");
			dateAndTime = userService.validateDateTime(appointmentRequest.getDateAndTime());
			
			logger.info("All inputs valid.. creating appointment");
			Appointment appointment = new Appointment();
			appointment.setCenter(center);
			appointment.setTest(test);
			appointment.setUser(user);
			appointment.setDateTime(dateAndTime);
			logger.info("Appointment created.. returning appointment object");
			return new ResponseEntity<Appointment>(userService.addAppointment(appointment),HttpStatus.OK);
		}catch(ValidationException exception) {
			logger.error("Caught validation exception in /addAppointment Controller");
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	/*
	 * Author: 			Jayesh Gaur
	 * Description: 	Returns a list of all appointments of the current user
	 * Input: 			User Id to identify the user
	 * Output: 			List of Appointments
	 * Created on:		October 14, 2019
	 */
	@GetMapping(value="/viewAppointments")
	public ResponseEntity<?> viewAppointments(@RequestParam("userId") String stringUserId){
		BigInteger userId = null;
		try {
			logger.info("Validating user Id entered for appointment");
			userId = userService.validateUserId(stringUserId);
			logger.info("Getting appointments of the user corresponding to the user id..");
			List<Appointment> appointmentList = userService.getAppointmentList(userId);
			if(appointmentList.size() > 0) {
				logger.info("Returning appointment list to the user..");
				return new ResponseEntity<List<Appointment>>(appointmentList, HttpStatus.OK);
			}else {
				logger.info("0 appointments in the system from this user.. returning nothing");
				return new ResponseEntity<String>("You have no appointments", HttpStatus.NO_CONTENT);
			}
			
		}catch(ValidationException exception) {
			logger.error("Caught validation exception in /viewAppointments Controller");
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * Author: 			Jayesh Gaur
	 * Description: 	Returns a list of all appointments of the center
	 * Input: 			center id
	 * Output: 			List of Appointments
	 * Created on:		October 14, 2019
	 */
	@GetMapping(value="/pendingAppointments")
	public ResponseEntity<?> pendingAppointments(@RequestParam("centerId") String stringCenterId){
		BigInteger centerId = null;
		try {
			logger.info("Validating center Id entered for appointment..");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			logger.info("Getting appointments of the user corresponding to the user id..");
			List<Appointment> appointmentList = userService.getCenterAppointmentList(centerId);
			if(appointmentList.size() > 0) {
				logger.info("Returning appointment list to the admin..");
				return new ResponseEntity<List<Appointment>>(appointmentList, HttpStatus.OK);
			}else {
				logger.info("0 appointments in the system from this center.. returning nothing");
				return new ResponseEntity<String>("No Appointments in this center", HttpStatus.NO_CONTENT);
			}
		}catch(ValidationException exception) {
			logger.error("Caught validation exception in /pendingAppointments Controller");
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * Author: 		Jayesh Gaur
	 * Description: Approves the appointment status from pending to approved
	 * Input: 		Appointment Id of the appointment to be approved
	 * Output: 		Status of the operation: true or error
	 * Created on: 	October 14, 2019
	 */
	@PostMapping(value="/approveAppointment")
	public ResponseEntity<?> approveAppointments(@RequestParam("appointmentId") String stringAppointmentId){
		BigInteger appointmentId = null;
		try {
			appointmentId = userService.validateAppointmentId(stringAppointmentId, userService.getAppointments());
			if(userService.approveAppointment(appointmentId)) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
			return null;
		}catch(ValidationException exception) {
			logger.error("Caught validation exception in /approveAppointment Controller");
			return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * Author: Jayesh Gaur 
	 * Description: Get Excel sheet consisting of appointment
	 * 					details for the user 
	 * Input: User Id
	 * Output: Excel download of the appointments
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel(@RequestParam("userId") String stringUserId) {
		BigInteger userId = null;
		try {
			logger.info("Validating user Id entered for appointment");
			userId = userService.validateUserId(stringUserId);
			logger.info("Getting appointments of the user corresponding to the user id..");
			List<Appointment> appointmentList = userService.getAppointmentList(userId);
			if(appointmentList.size() > 0) {
				logger.info("Returning appointment list to the user..");
				return new ModelAndView((View) new ExcelReportView(), "appointmentList", appointmentList);
			}else {
				logger.info("0 appointments in the system from this user.. returning nothing");
			//	return new ResponseEntity<String>("You have no appointments", HttpStatus.NO_CONTENT);
			}
		
		}catch(ValidationException exception) {
			logger.error("Caught validation exception in /viewAppointments Controller");
	//		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}	return null;
	}
}
