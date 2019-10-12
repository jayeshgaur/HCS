package com.cg.healthcaresystembootmvc.controller;

/*
 * author: Jayesh Gaur, Nidhi, Kushal Khurana
 */

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;
import com.cg.healthcaresystembootmvc.dto.Test;
import com.cg.healthcaresystembootmvc.dto.User;
import com.cg.healthcaresystembootmvc.exceldownload.ExcelReportView;
import com.cg.healthcaresystembootmvc.exception.ExistingCredentialException;
import com.cg.healthcaresystembootmvc.exception.ValidationException;
import com.cg.healthcaresystembootmvc.service.UserService;

@ComponentScan
@Controller
public class HCSController {
	
	/*
	 * Author: Kushal Khurana
	 * Created on: October 11, 2019
	 */
	private static final Logger logger = LoggerFactory.getLogger(HCSController.class);

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	/*
	 * Author: Jayesh Gaur 
	 * Description: Default Mapper. Opens Home.jsp by default when the project is run
	 * Created: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultMapper() {
		logger.info("returning to home page");
		return "Home";
	}

	/*
	 * Author: Jayesh Gaur Description: Map /Home to Home.jsp Created: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String HomeMapper() {
		logger.info("returning to Home.jsp");
		return "Home";
	}

	/*
	 * Author: Jayesh Gaur Description: Get login page Created: October 9, 2019
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginpage() {
		logger.info("Returning Login.jsp page");
		return "Login";
	}

	/*
	 * Author: Jayesh Gaur Description: Get Admin Home page Created: October 9, 2019
	 */
	@RequestMapping(value = "/AdminHome", method = RequestMethod.GET)
	public String adminHomePage() {
		logger.info("Returning AdminHome.jsp page");
		return "AdminHome";
	}

	/*
	 * Author: Jayesh Gaur Description: Get User Home page Created: October 9, 2019
	 */
	@RequestMapping(value = "/UserHome", method = RequestMethod.GET)
	public String userHomePage() {
		logger.info("Returning UserHome.jsp page");
		return "UserHome";
	}

	/*
	 * Author:			Jayesh Gaur
	 * Description: 	Checks user credentials and sets session attributes depending on the type of user logging in
	 * Input: 			User credentials, email Id and password
	 * Output: 			Admins are returned to AdminHome.jsp
	 * 					Customers are returned to UserHome.jsp
	 * Created on: 		October 9, 2019
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model) {
		logger.info("Checking login credentials..");
		
		//Check admin credentials
		if (email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			//Log in the user as admin if login is successful
			logger.info("Admin Authenticated.. setting userRole as admin and returning AdminHome.jsp");
			session.setAttribute("userRole", "admin");
			return "AdminHome";
		} else {
			//Check customer credentials, log in the user with his id if he's successfully authenticated
			logger.info("User is not an admin.. checking customer credentials..");
			BigInteger userId = userService.userLogin(email, password);
			if (null != userId) {
				logger.info("User authenticated... User id: "+userId+". Returning to UserHome.jsp");			
				session.setAttribute("userId", userId);
				return "UserHome";
			} else {
				//if credentials don't match, redirect user back to login page with error message
				logger.error("Invalid user credentials.. retuning back to Login.jsp");
				model.put("errormessage", "Invalid credentials");
				return "Login";
			}
		}
	}

	/*
	 * Author: Jayesh Gaur 
	 * Description: Get registration page 
	 * Created: October 9, 2019
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(@ModelAttribute("customer") User user) {
		logger.info("Returning Register page..");
		return "Registration";
	}

	/*
	 * Author: 		Jayesh Gaur 
	 * Description: Registers the new user into the system if
	 * 					all the validation tests are passed 
	 * Created: 	October 9, 2019 
	 * Input:		User details in the form of User object 
	 * Output: 		Returns the newly registered user
	 * 					to his homepage and automatically logs him in
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("customer") User user, BindingResult bindingResult,
			Map<String, Object> model) {
		BigInteger userId;
		// Check if the validation tests are passed. Return the user back to registration page if any test fails
		logger.info("Checking user details entered for registration...");
		if (bindingResult.hasErrors()) {
			logger.error("Improper registration details... retuning back to registration.jsp");
			return "Registration";
		} else {
			logger.info("Center details passed validation... proceeding");
			// Register the user and get his automatically generated user Id
			try {
			logger.info("Calling Service to register the user");
			userId = userService.register(user);
			// Log the user in and set his user ID into the session object and send him to user home page
			logger.info("Registration successful.. logging the user in");
			session.setAttribute("userId", userId);
			model.put("userId", userId);
			}catch(ExistingCredentialException exception) {
				//if registration failed, return back to registration page with proper error message
				logger.error("Caught ExistingCredentialException in register post, returning to Registration page");
				model.put("duplicate",exception.getMessage());
				return "Registration";
			}
			logger.info("New registered user logged in, returning to user home page");
			return "UserHome";
		}
	}
	
	
	/*
	 * Author:			Kushal Khurana
	 * Description: 	Get AddCenter Page	 
	 * Created on: 		October 11, 2019
	 */
	
	@RequestMapping(value = "/AddCenter", method = RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("Center") DiagnosticCenter center) {
		logger.info("Returning Admin to Add Center Page");
		return "addCenter";
	}
	
	/*
	 * Author:			Kushal Khurana
	 * Description: 	Adding new Center	 
	 * Input: 			Center Details: Name, Contact Number and Address
	 * Output: 			Inputs will be saved in the data					
	 * Created on: 		October 11, 2019
	 */
	
	@RequestMapping(value = "/AddCenter", method = RequestMethod.POST)
	public String addCenter(@Valid @ModelAttribute("Center") DiagnosticCenter center, BindingResult result,
			Map<String, Object> model) {
		logger.info("Checking Center inputs.");
		
	
		if (result.hasErrors()) {
			//If inputs are not according to validations, will ask to try again.
			logger.info("ImProper Details, returning back to Add Center Page");
			model.put("error", "Please enter details according to the given messages");
			return "addCenter";
		} else {
			//Showing message after adding new center details in the data. and returning to Admin Home Page.
			logger.info("Proper Center Details Added, Returning to AdminHome Page. ");
			userService.addCenter(center);
			model.put("message", "New Center with details added successfully");
			return "AdminHome";
		}
	}
	
	/*
	 * Author: 			Jayesh Gaur
	 * Description: 	Retrieves a list of all centers from the database and returns the view
	 * 					ShowCenters with the model of list of centers.
	 * Created on: 		October 9, 2019
	 */
	@RequestMapping(value = "/showAllCenter", method = RequestMethod.GET)
	public ModelAndView getAllData() {
		logger.info("Calling service to get center list");
		List<DiagnosticCenter> myList = userService.getCenterList();
		return new ModelAndView("ShowCenters", "data", myList);
	}

	/*
	 * Author : Nidhi
	 * Description : This controller method maps the url /Test/Add and redirect the controller to addTest.jsp
	 * Created Date : 9th October,2019
	 * Input: Map<String,Object> model
	 * Return Type : return addTest string
	 * 
	 * */
	@RequestMapping(value = "/AddTest", method = RequestMethod.GET)
	public String addTestPage(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "addTest";
	}

	/*
	 * Author : Nidhi
	 * Description : This controller method maps the url /Test/Add and calls the method 
	 * of userService for server side validation for centerid and for adding test by calling the method of userService addTest
	 * Created Date : 9th October,2019
	 * Input: String stringCenterId,String testName,Map<String, Object> model
	 * Return Type : return addTest string
	 * 
	 * */
	
	@RequestMapping(value = "/AddTest", method = RequestMethod.POST)
	public String addTestSubmit(@RequestParam("centerId") String stringCenterId,
			@RequestParam("testName") String testName, Map<String, Object> model) {
		BigInteger centerId = null;
		try {
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			if (null != userService.addTest(centerId, new Test(testName)))
				model.put("message", "Added successfully");
		} catch (ValidationException exception) {
			model.put("message", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
		return "addTest";
	}

	@RequestMapping(value = "/DeleteCenter", method = RequestMethod.GET)
	public String deleteCenterRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "deleteCenter";
	}

	@RequestMapping(value = "/DeleteCenter", method = RequestMethod.POST)
	public String deleteCenter(@RequestParam("centerId") String stringCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		DiagnosticCenter center = null;
		try {
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			center = userService.findCenter(centerId);
			model.put("center", center);
		} catch (ValidationException exception) {
			model.put("centerList", userService.getCenterList());
			model.put("deleteMessage", exception.getMessage());
		}
		return "deleteCenter";

	}

	@RequestMapping(value = "/ConfirmDelete", method = RequestMethod.POST)
	public String confirmDeleteCenter(@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {

		if (userService.removeCenter(centerId)) {
			model.put("deleteMessage", "Deleted successfully");
		} else {
			model.put("deleteMessage", "Could not delete, please try again");
		}
		model.put("centerList", userService.getCenterList());
		return "deleteCenter";
	}


	/*
	 * Author : Nidhi
	 * Description : This controller method maps the url /Test/Remove and redirect the controller to deleteTest.jsp
	 * Created Date : 9th October,2019
	 * Input: Map<String,Object> model
	 * Return Type : return deleteTest string
	 * 
	 * */
	
	@RequestMapping(value = "/RemoveTest", method = RequestMethod.GET)
	public String deleteTestRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "deleteTest";
	}
	/*
	 * Author : Nidhi
	 * Description : This controller method will first validate the centerid using the service method validateCenterId and put the list 
	 * of tests of that particular center into the Map model.
	 * Created Date : 9th October,2019
	 * Input: String stringCenterId, Map<String,Object> model
	 * Return Type : return deleteTest string
	 * 
	 * */

	@RequestMapping(value = "/SelectCenter", method = RequestMethod.POST)
	public String deleteTestSelectCenter(@RequestParam("centerId") String stringCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		try {
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			List<Test> testList = userService.getListOfTests(centerId);
			if (testList.size() > 0) {
				session.setAttribute("centerId", centerId);
				model.put("testList", userService.getListOfTests(centerId));
			} else {
				model.put("errorMessage", "No Tests present");
			}
		} catch (ValidationException exception) {
			model.put("centerList", userService.getCenterList());
			model.put("errorMessage", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
		return "deleteTest";
	}

	/*
	 * Author : Nidhi
	 * Description : This controller method will validate the testid using the service method validateTestId and redirect the controller to deleteTest
	 * Created Date : 9th October,2019
	 * Input: String stringCenterId, Map<String,Object> model
	 * Return Type : return deleteTest string
	 * 
	 * */
	
	@RequestMapping(value = "/SelectTest", method = RequestMethod.POST)
	public String deleteTest(@RequestParam("testId") String sTestId, Map<String, Object> model) {
		BigInteger testId = null;
		try {
			testId = userService.validateTestId(sTestId,
					userService.getListOfTests((BigInteger) session.getAttribute("centerId")));
			session.setAttribute("testId", testId);
			model.put("testId", testId);
		} catch (ValidationException exception) {
			model.put("testErrorMessage", exception.getMessage());
		}
		// userService.removeTest(centerId, testId);
		model.put("testList", userService.getListOfTests((BigInteger) session.getAttribute("centerId")));
		model.put("centerList", userService.getCenterList());
		return "deleteTest";
	}

	/*
	 * Author : Nidhi
	 * Description : This controller method will remove the test from the specified center.
	 * of tests of that particular center into the Map model.
	 * Created Date : 9th October,2019
	 * Input: BigInteger testId,BigInteger centerId, Map<String,Object> model
	 * Return Type : return AdminHome string
	 * 
	 * */
	
	@RequestMapping(value = "/TestConfirm", method = RequestMethod.POST)
	public String deleteTestConfirm(@RequestParam("testId") BigInteger testId,
			@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) throws ValidationException {
		if (userService.removeTest(centerId, testId)) {
			session.setAttribute("testId", null);
			session.setAttribute("centerId", null);
			model.put("message", "Deleted Successfully");
		} else {
			model.put("message", "Error. Please try after some time.");
		}
		return "AdminHome";
	}

	/*
	 * Author: 			Jayesh Gaur
	 * Description:		Ends the user session by setting null to the session objects
	 * 					Redirects to the Login page
	 * Created on: 		October 9, 2019 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		//remove session variables
		logger.info("Releasing session variable details..");
		session.setAttribute("userRole", null);
		session.setAttribute("userId", null);
		logger.info("User logged out successfully... return to Login page");
		return "Login";
	}

	/*
	 * Author: 		Jayesh Gaur 
	 * Description: Get Add Appointment Page for the user
	 * Created on: 	October 9, 2019
	 */
	@RequestMapping(value = "/addAppointment", method = RequestMethod.GET)
	public String addAppointment(Map<String, Object> model) {
		logger.info("Retrieving center List to send to add appointment page");
		model.put("centerList", userService.getCenterList());
		logger.info("Returning to add appointment page");
		return "addAppointment";
	}

	/*
	 * Author: Jayesh Gaur 
	 * Description: Processes the center Id received by the
	 * 				user, validates it, and returns a list of tests under the center
	 * 				corresponding to the center id received from the user 
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/SelectTests", method = RequestMethod.POST)
	public String addAppointmentSelectTest(@RequestParam("centerId") String stringCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		List<Test> testList;
		try {
			// Validate the center id entered by the user and return it's BigInteger value if the ID is valid.
			// Throws ValidationException if the centerId is not valid
			logger.info("validating center id received");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());

			// Get list of tests under the center corresponding to the center Id.
			logger.info("Center Id Validated. Getting list of tests under that center");
			testList = userService.getListOfTests(centerId);

			// Return the test list if tests are present
			logger.info("Checking if test list is empty..");
			if (testList.size() > 0) {
				logger.info("Test list not empty. adding test list model to the view");
				model.put("testList", testList);
				session.setAttribute("centerId", centerId);
			} else {
				// Return the centerList again to the user and redirect to the previous page if no tests exist in the center
				logger.info("No test list present");
				model.put("message", "Sorry, no tests present in that center.");
			}
		} catch (ValidationException exception) {
			logger.error("Caught validation exception in select tests add appointment");
			model.put("message", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
		logger.info("Returning to addAppointment with test list in the selected center..");
		return "addAppointment";
	}

	/*
	 * Author: Jayesh Gaur 
	 * Description: Validate the test id and datetime received 
	 * 				from the user and book an appointment in the system
	 * Created on: October 9, 2019
	 */	
	@RequestMapping(value = "/confirmAppointment", method = RequestMethod.POST)
	public String addAppointment(@RequestParam("testId") String sTestId, @RequestParam("dateAndTime") String sDateTime,
			@RequestParam("userId") BigInteger userId, @RequestParam("centerId") BigInteger centerId,
			Map<String, Object> model) {
		Appointment appointment = new Appointment();
		//Get the center object corresponding to the center id
		DiagnosticCenter center = userService.findCenter(centerId);
		LocalDateTime dateTime;
		BigInteger testId;
		List<Test> testList = userService.getListOfTests((BigInteger) session.getAttribute("centerId"));
		try {
			//Validate test Id, return biginteger value of testId if passed
			testId = userService.validateTestId(sTestId, testList);
			
			//Validate date and time, return LocalDateTime value of datetime if passed
			dateTime = userService.validateDateTime(sDateTime);
			
			//Get the test object corresponding to the test id
			Test test = userService.findTest(testId);
			
			//Get the user object corresponding to the user id
			User user = userService.findUser(userId);
			
			//By default, all new appointments have "pending" status
			appointment.setAppointmentStatus(0);
			appointment.setCenter(center);
			System.out.println(sDateTime);
			appointment.setDateTime(dateTime);
			appointment.setTest(test);
			appointment.setUser(user);
			userService.addAppointment(appointment);
			model.put("message", "Appointment booked successfully");
		} catch (ValidationException exception) {
			model.put("centerList", userService.getCenterList());
			model.put("testList", testList);
			model.put("testmessage", exception.getMessage());
			return "addAppointment";
		}
		session.setAttribute("centerId", null);
		return "UserHome";
	}

	/*
	 * author: 		Jayesh Gaur
	 * Description: Get Approve Appointment Page
	 * Created on: 	October 9, 2019
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public String approveAppointment(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "ApproveAppointment";
	}

	/*
	 * Author:		Jayesh Gaur
	 * Description: Validates the center Id entered by user and returns a list of all
	 * 				appointments under the center corresponding to the center Id in the view
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/approveCenter", method = RequestMethod.POST)
	public String approveAppointmentSelectCenter(@RequestParam("centerId") String sCenterId,
			Map<String, Object> model) {
		BigInteger centerId = null;
		try {
			//Validate center Id
			centerId = userService.validateCenterId(sCenterId, userService.getCenterList());

			List<Appointment> appointmentList = userService.getCenterAppointmentList(centerId);
			if (appointmentList.size() > 0) {
				session.setAttribute("centerId", centerId);
				model.put("appointmentList", appointmentList);
			} else {
				model.put("errorMessage", "No Appointments to be approved");
			}
		} catch (ValidationException exception) {
			model.put("centerList", userService.getCenterList());
			model.put("errorMessage", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
		return "ApproveAppointment";
	}

	/*
	 * Author:		Jayesh Gaur
	 * Description: Validates the appointment Id entered by user and returns the admin
	 * 				to the same page and asks for confirmation before approving the 
	 * 				appointment
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/approveAppointment", method = RequestMethod.POST)
	public String approveAppointment(@RequestParam("appointmentId") String sAppointmentId, Map<String, Object> model) {
		BigInteger appointmentId = null;
		List<Appointment> appointmentList = userService
				.getCenterAppointmentList((BigInteger) session.getAttribute("centerId"));
		try {
			appointmentId = userService.validateAppointmentId(sAppointmentId, appointmentList);
			session.setAttribute("appointmentId", appointmentId);
			model.put("appointmentId", appointmentId);
		} catch (ValidationException exception) {
			model.put("appointmentErrorMessage", exception.getMessage());
		}
		model.put("appointmentList", appointmentList);
		model.put("centerList", userService.getCenterList());
		return "ApproveAppointment";
	}

	/*
	 * Author:		Jayesh Gaur
	 * Description: Approves the appointment in the database and returns the admin to
	 * 				admin home page.
	 * Created on: 	October 9, 2019
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public String approveAppointmentConfirm(@RequestParam("appointmentId") BigInteger appointmentId,
			@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {
		if (userService.approveAppointment(appointmentId)) {
			session.setAttribute("appointmentId", null);
			session.setAttribute("centerId", null);
			model.put("message", "Approved Successfully");
		} else {
			model.put("message", "Error. Please try after some time.");
		}
		return "AdminHome";
	}

	/*
	 * Author: 		Jayesh Gaur 
	 * Description: Get View Appointment Page which displays ALL appointments of the logged 
	 * 				in user 
	 * Created on: October 9, 2019
	 */	
	@RequestMapping(value = "viewAppointment", method = RequestMethod.GET)
	public String viewUserAppointments(Map<String, Object> model) {
		
		List<Appointment> userAppointmentList = userService
				.getAppointmentList((BigInteger) session.getAttribute("userId"));
		model.put("appointmentList", userAppointmentList);
		return "viewUserAppointments";
	}

	/*
	 * Author: 		Jayesh Gaur 
	 * Description: Get Excel sheet consisting of appointment details for the user
	 * Created on: October 9, 2019
	 */	
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		List<Appointment> appointmentList = userService.getAppointmentList((BigInteger) session.getAttribute("userId"));
		return new ModelAndView((View) new ExcelReportView(), "appointmentList", appointmentList);
	}

}
