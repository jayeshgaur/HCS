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
	 * Author: Kushal Khurana Created on: October 11, 2019
	 */
	private static final Logger logger = LoggerFactory.getLogger(HCSController.class);

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	/*
	 * Author: Jayesh Gaur Description: Default Mapper. Opens Home.jsp by default
	 * when the project is run Created: October 9, 2019
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
		logger.info("Returning Home.jsp..");
		return "Home";
	}

	/*
	 * Author: Jayesh Gaur Description: Get login page Created: October 9, 2019
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginpage() {
		logger.info("Returning Login.jsp..");
		return "Login";
	}

	/*
	 * Author: Jayesh Gaur Description: Get Admin Home page Created: October 9, 2019
	 */
	@RequestMapping(value = "/AdminHome", method = RequestMethod.GET)
	public String adminHomePage() {

		logger.info("Returning AdminHome.jsp..");

		return "AdminHome";
	}

	/*
	 * Author: Jayesh Gaur Description: Get User Home page Created: October 9, 2019
	 */
	@RequestMapping(value = "/UserHome", method = RequestMethod.GET)
	public String userHomePage() {
		logger.info("Returning UserHome.jsp..");
		return "UserHome";
	}

	/*
	 * Author: Jayesh Gaur Description: Checks user credentials and sets session
	 * attributes depending on the type of user logging in Input: User credentials,
	 * email Id and password Output: Admins are returned to AdminHome.jsp Customers
	 * are returned to UserHome.jsp Created on: October 9, 2019
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model) {
		logger.info("Checking login credentials..");
		// Check admin credentials
		if (email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			// Log in the user as admin if login is successful
			logger.info("Admin Authenticated.. setting userRole as admin and returning AdminHome.jsp");
			session.setAttribute("userRole", "admin");
			return "AdminHome";
		} else {
			// Check customer credentials, log in the user with his id if he's successfully
			// authenticated
			logger.info("User is not an admin.. checking customer credentials..");
			BigInteger userId = userService.userLogin(email, password);
			if (null != userId) {
				logger.info("User authenticated... User id: " + userId + ". Returning to UserHome.jsp");
				session.setAttribute("userId", userId);
				return "UserHome";
			} else {
				// if credentials don't match, redirect user back to login page with error
				// message
				logger.error("Invalid user credentials.. retuning back to Login.jsp");
				model.put("errormessage", "Invalid credentials");
				return "Login";
			}
		}
	}

	/*
	 * Author: Jayesh Gaur Description: Get registration page Created: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(@ModelAttribute("customer") User user) {
		logger.info("Returning Register page..");
		return "Registration";
	}

	/*
	 * Author: Jayesh Gaur Description: Registers the new user into the system if
	 * all the validation tests are passed Created: October 9, 2019 Input: User
	 * details in the form of User object Output: Returns the newly registered user
	 * to his homepage and automatically logs him in
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("customer") User user, BindingResult bindingResult,
			Map<String, Object> model) {
		BigInteger userId;
		// Check if the validation tests are passed. Return the user back to
		// registration page if any test fails
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
				// Log the user in and set his user ID into the session object and send him to
				// user home page
				logger.info("Registration successful.. logging the user in");
				session.setAttribute("userId", userId);
				model.put("userId", userId);
			} catch (ExistingCredentialException exception) {
				// if registration failed, return back to registration page with proper error
				// message
				logger.error("Caught ExistingCredentialException in register post, returning to Registration page");
				model.put("duplicate", exception.getMessage());
				return "Registration";
			}
			logger.info("New registered user logged in, returning to user home page");
			return "UserHome";
		}
	}

	/*
	 * Author: Kushal Khurana Description: Get AddCenter Page Created on: October
	 * 11, 2019
	 */

	@RequestMapping(value = "/AddCenter", method = RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("Center") DiagnosticCenter center) {
		logger.info("Returning Admin to Add Center Page");
		return "addCenter";
	}

	/*
	 * Author: Kushal Khurana Description: Adding new Center Input: Center Details:
	 * Name, Contact Number and Address Output: Inputs will be saved in the data
	 * Created on: October 11, 2019
	 */

	@RequestMapping(value = "/AddCenter", method = RequestMethod.POST)
	public String addCenter(@Valid @ModelAttribute("Center") DiagnosticCenter center, BindingResult result,
			Map<String, Object> model) {
		logger.info("Checking Center inputs.");
		

		if (result.hasErrors()) {
			// If inputs are not according to validations, will ask to try again.
			logger.info("ImProper Details, returning back to Add Center Page");
			model.put("error", "Please enter details according to the given messages");
			return "addCenter";
		} else {
			// Showing message after adding new center details in the data. and returning to
			// Admin Home Page.
			logger.info("Proper Center Details Added, Returning to AdminHome Page. ");
			userService.addCenter(center);
			model.put("message", "New Center with details added successfully");
			return "AdminHome";
		}
	}

	/*
	 * Author: Jayesh Gaur Description: Retrieves a list of all centers from the
	 * database and returns the view ShowCenters with the model of list of centers.
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/showAllCenter", method = RequestMethod.GET)
	public ModelAndView getAllData() {
		logger.info("Calling service to get center list");
		List<DiagnosticCenter> myList = userService.getCenterList();
		return new ModelAndView("ShowCenters", "data", myList);
	}

	/*
	 * Author : Nidhi Description : This controller method maps the url /Test/Add
	 * and redirect the controller to addTest.jsp Created Date : 9th October,2019
	 * Input: Map<String,Object> model Return Type : return addTest string
	 * 
	 */
	@RequestMapping(value = "/AddTest", method = RequestMethod.GET)
	public String addTestPage(Map<String, Object> model) {

		logger.info("Returning Admin to Add Test Page");
		model.put("centerList", userService.getCenterList());
		// getting Center List on Add Test Page
		return "addTest";
	}

	/*
	 * Author : Nidhi Description : This controller method maps the url /Test/Add
	 * and calls the method of userService for server side validation for centerid
	 * and for adding test by calling the method of userService addTest Created Date
	 * : 9th October,2019 Input: String stringCenterId,String testName,Map<String,
	 * Object> model Return Type : return addTest string
	 * 
	 */

	@RequestMapping(value = "/AddTest", method = RequestMethod.POST)
	public String addTestSubmit(@RequestParam("centerId") String stringCenterId,
			@RequestParam("testName") String testName, Map<String, Object> model) {
		// Posting new Test Details in a given center
		BigInteger centerId = null;
		try {
			// validating Center Id
			logger.info("Checking Center if it is present in  data or not");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			if (null != userService.addTest(centerId, new Test(testName)))
				logger.info("New Test has been added in a particular center");
			// Adding new test in a center using center ID
			model.put("message", "Added successfully");
		} catch (ValidationException exception) {

			// if inserted improper Center Id, return back to Add Test page with proper
			// error message
			logger.error("Inserted improper ID, printing error message and  returning to Add Test page.");
			model.put("message", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
		return "addTest";
	}

	/*
	 * Author: Kushal Khurana Description: Getting Delete Center page with Center
	 * List Created on: October 11, 2019
	 */
	@RequestMapping(value = "/DeleteCenter", method = RequestMethod.GET)
	public String deleteCenterRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		// Using Center List to select which Center you want to delete.
		logger.info("Getting Center Details to delete among the list.");
		return "deleteCenter";
	}

	/*
	 * Author: Kushal Khurana Description: Deleting Center Input: Center ID Output:
	 * Center Details with respect to center id. Created on: October 11, 2019
	 */

	@RequestMapping(value = "/DeleteCenter", method = RequestMethod.POST)
	public String deleteCenter(@RequestParam("centerId") String stringCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		DiagnosticCenter center = null;
		try {
			// Validation of Center Id
			logger.info("Validating enter Id");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			logger.info("Getting center Id entered by Admin");
			center = userService.findCenter(centerId);
			// Checking Center Id if it is present in the data or not.
			logger.info("Finding Center Details from CenterID in the data");
			model.put("center", center);
		} catch (ValidationException exception) {
			logger.error("Showing Error if the center Id entered doesn't match with data ");
			model.put("centerList", userService.getCenterList());
			model.put("deleteMessage", exception.getMessage());
		}
		// Returning to Delete Center Page
		return "deleteCenter";

	}

	/*
	 * Author: Kushal Khurana Description: Confirmation for Deleting Center Created
	 * on: October 11, 2019
	 */
	@RequestMapping(value = "/ConfirmDelete", method = RequestMethod.POST)
	public String confirmDeleteCenter(@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {
		// Confirmation before Deleting a Center from the Database
		logger.info("Delete Center confirmation");
		try {
			
		if (userService.removeCenter(centerId)) {
			logger.info("Center Id matched with Database, Deleting Center from the datbase");
			model.put("deleteMessage", "Deleted successfully");
		} else {
			// Error Message to try again
			model.put("deleteMessage", "Could not delete, please try again");
		}
		}
		catch(ValidationException exception){
			model.put("Invalid center Id", exception.getMessage());
			
		}
		
		// showing Updated center List
		logger.info("Updated Center List Displayed on the same Page");
		model.put("centerList", userService.getCenterList());
		logger.info("Returning to Delete Center");
		return "deleteCenter";
	}

	/*
	 * Author : Nidhi Description : This controller method maps the url /Test/Remove
	 * and redirect the controller to deleteTest.jsp Created Date : 9th October,2019
	 * Input: Map<String,Object> model Return Type : return deleteTest string
	 * 
	 */

	@RequestMapping(value = "/RemoveTest", method = RequestMethod.GET)
	public String deleteTestRequest(Map<String, Object> model) {
		//Showing Center List on Delete Test Page
		logger.info("Getting Center List to delete Test from particular Center");
		model.put("centerList", userService.getCenterList());
		return "deleteTest";
	}

	/*
	 * Author : Nidhi Description : This controller method will first validate the
	 * centerid using the service method validateCenterId and put the list of tests
	 * of that particular center into the Map model. Created Date : 9th October,2019
	 * Input: String stringCenterId, Map<String,Object> model Return Type : return
	 * deleteTest string
	 */

	@RequestMapping(value = "/SelectCenter", method = RequestMethod.POST)
	//Selecting Center for Deleting test from it.
	public String deleteTestSelectCenter(@RequestParam("centerId") String stringCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		try {
			logger.info("Validating Center ID for Removing Test");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			logger.info("Retrieving Test List for a Particular Center");
			List<Test> testList = userService.getListOfTests(centerId);
			//Checking if the list contains the Tests or not
			if (testList.size() > 0) {
				//retrieving Center ID for Next Pages for a particular session
				session.setAttribute("centerId", centerId);
				//Getting Test List from the selected Center  
				logger.info("Showing Test present in the selected center");
				model.put("testList", userService.getListOfTests(centerId));
			} else {
				//Error showing of no selected test in the given center
				model.put("errorMessage", "No Tests present");
			}
		} catch (ValidationException exception) {
			//centerId Validation
			logger.error("Validating the Selected center Id for Removing Test");
			model.put("errorMessage", exception.getMessage());
		}
		//Getting Center List for selecting Test to delete it.
		logger.info("Displayed Center List to select a Test");
		model.put("centerList", userService.getCenterList());
		return "deleteTest";
	}

	/*
	 * Author : Nidhi Description : This controller method will validate the testid
	 * using the service method validateTestId and redirect the controller to
	 * deleteTest Created Date : 9th October,2019 Input: String stringCenterId,
	 * Map<String,Object> model Return Type : return deleteTest string
	 * 
	 */
	@RequestMapping(value = "/SelectTest", method = RequestMethod.POST)
	//now we are selecting Test using Test Id
	public String deleteTest(@RequestParam("testId") String sTestId, Map<String, Object> model) {
		BigInteger testId = null;
		
		try {
			//validating if the test Id is present or not and getting list of Test
			logger.info("Validating test ID for Removing Test");
			testId = userService.validateTestId(sTestId,
					userService.getListOfTests((BigInteger) session.getAttribute("centerId")));
			session.setAttribute("testId", testId);
			model.put("testId", testId);
		} catch (ValidationException exception) {
			
			//validating Test id
			logger.error("Error Message for providing improper TestId");
			model.put("testErrorMessage", exception.getMessage());
		}
		// userService.removeTest(centerId, testId);
		
		//showing Center List and test List through Center and test Ids
		logger.info("shown Center and Test List for Deleting a particular test");
		model.put("testList", userService.getListOfTests((BigInteger) session.getAttribute("centerId")));
		model.put("centerList", userService.getCenterList());
		logger.info("Returning back to Delete Test page");
		return "deleteTest";
	}

	/*
	 * Author : Nidhi Description : This controller method will remove the test from
	 * the specified center. of tests of that particular center into the Map model.
	 * Created Date : 9th October,2019 Input: BigInteger testId,BigInteger centerId,
	 * Map<String,Object> model Return Type : return AdminHome string
	 * 
	 */

	@RequestMapping(value = "/TestConfirm", method = RequestMethod.POST)
	//Confirmation for deleting Test from the database
	public String deleteTestConfirm(@RequestParam("testId") BigInteger testId,
			@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) throws ValidationException {
		if (userService.removeTest(centerId, testId)) {
			
			logger.info("Releasing session variable details");
			session.setAttribute("testId", null);
			session.setAttribute("centerId", null);
			//message for successfully deleting a test
			logger.info("Succesfully deleted test from a center ");
			model.put("message", "Deleted Successfully");
		} else {
			//error message for trying again to delete test
			model.put("message", "Error. Please try after some time.");
		}
		//back to Admin home
		logger.info("Returning back to Admin Home after Removing test from the database");
		return "AdminHome";
	}

	/*
	 * Author: Jayesh Gaur Description: Ends the user session by setting null to the
	 * session objects Redirects to the Login page Created on: October 9, 2019
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		// remove session variables
		logger.info("Releasing session variable details..");
		session.setAttribute("userRole", null);
		session.setAttribute("userId", null);
		logger.info("User logged out successfully... return to Login page");
		return "Login";
	}

	/*
	 * Author: Jayesh Gaur Description: Get Add Appointment Page for the user
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/addAppointment", method = RequestMethod.GET)
	public String addAppointment(Map<String, Object> model) {
		logger.info("Retrieving center List to send to add appointment page");
		model.put("centerList", userService.getCenterList());
		logger.info("Returning to add appointment page");
		return "addAppointment";
	}

	/*
	 * Author: Jayesh Gaur Description: Processes the center Id received by the
	 * user, validates it, and returns a list of tests under the center
	 * corresponding to the center id received from the user Created on: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/SelectTests", method = RequestMethod.POST)
	public String addAppointmentSelectTest(@RequestParam("centerId") String stringCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		List<Test> testList;
		try {
			// Validate the center id entered by the user and return it's BigInteger value
			// if the ID is valid.
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
				//store the centerId in session for future use
				session.setAttribute("centerId", centerId);
			} else {
				// Return the centerList again to the user and redirect to the previous page if
				// no tests exist in the center
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
	 * Author: Jayesh Gaur Description: Validate the test id and datetime received
	 * from the user and book an appointment in the system Created on: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/confirmAppointment", method = RequestMethod.POST)
	public String addAppointment(@RequestParam("testId") String stringTestId, @RequestParam("dateAndTime") String stringDateTime,
			Map<String, Object> model) {
		// Get the center object corresponding to the center id
		logger.info("Getting the center object corresponding to the centerId");
		DiagnosticCenter center = userService.findCenter((BigInteger) session.getAttribute("centerId"));
		LocalDateTime dateTime;
		BigInteger testId;
		logger.info("Getting the testList of the selected center again for validation..");
		List<Test> testList = userService.getListOfTests((BigInteger) session.getAttribute("centerId"));
		try {
			// Validate test Id, return biginteger value of testId if passed
			logger.info("Validating the test id entered by the user...");
			testId = userService.validateTestId(stringTestId, testList);

			// Validate date and time, return LocalDateTime value of datetime if passed
			logger.info("Validating the date and time entered by the user...");
			dateTime = userService.validateDateTime(stringDateTime);

			// Get the test object corresponding to the test id
			logger.info("Getting the test object corresponding to the test Id selected..");
			Test test = userService.findTest(testId);

			// Get the user object corresponding to the user id
			logger.info("Getting the user object corresponding to the user id of the logged in customer");
			User user = userService.findUser((BigInteger) session.getAttribute("userId"));

			// By default, all new appointments have "pending" status
			logger.info("Creating new Appointment object..");
			Appointment appointment = new Appointment();
			
			logger.info("Initializing the appointment object created..");
			appointment.setAppointmentStatus(0);
			appointment.setCenter(center);
			appointment.setDateTime(dateTime);
			appointment.setTest(test);
			appointment.setUser(user);
			
			logger.info("Calling Service method to add the appointment..");
			userService.addAppointment(appointment);
			
			logger.info("Appointment booked successfully");
			model.put("message", "Appointment booked successfully");
		} catch (ValidationException exception) {
			logger.error("Caught validation exception in add appointment in controller..");
			model.put("centerList", userService.getCenterList());
			model.put("testList", testList);
			model.put("testmessage", exception.getMessage());
			return "addAppointment";
		}
		logger.info("Clearing the center Id session variable.. and returning to UserHome page");
		session.setAttribute("centerId", null);
		return "UserHome";
	}

	/*
	 * author: Jayesh Gaur Description: Get Approve Appointment Page Created on:
	 * October 9, 2019
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public String approveAppointment(Map<String, Object> model) {
		logger.info("Retrieving center List to send to approve appointment page");
		model.put("centerList", userService.getCenterList());
		logger.info("Returning to ApproveAppointment page");
		return "ApproveAppointment";
	}

	/*
	 * Author: Jayesh Gaur Description: Validates the center Id entered by user and
	 * returns a list of all appointments under the center corresponding to the
	 * center Id in the view Created on: October 9, 2019
	 */
	@RequestMapping(value = "/approveCenter", method = RequestMethod.POST)
	public String approveAppointmentSelectCenter(@RequestParam("centerId") String sCenterId,
			Map<String, Object> model) {
		BigInteger centerId = null;
		try {
			// Validate center Id
			logger.info("Validating the center Id entered by the admin");
			centerId = userService.validateCenterId(sCenterId, userService.getCenterList());

			logger.info("Getting list of pending appointments in that center");
			List<Appointment> appointmentList = userService.getCenterAppointmentList(centerId);
			if (appointmentList.size() > 0) {
				logger.info("Appointment list not empty.. setting center Id into session for future use");
				session.setAttribute("centerId", centerId);
				model.put("appointmentList", appointmentList);
			} else {
				logger.error("Empty appointment list...");
				model.put("errorMessage", "No Appointments to be approved");
			}
		} catch (ValidationException exception) {
			logger.error("Caught validation exception in /approveCenter in controller");
			model.put("centerList", userService.getCenterList());
			model.put("errorMessage", exception.getMessage());
		}
		logger.error("returning to ApproveAppointment page..");
		model.put("centerList", userService.getCenterList());
		return "ApproveAppointment";
	}

	/*
	 * Author: Jayesh Gaur Description: Validates the appointment Id entered by user
	 * and returns the admin to the same page and asks for confirmation before
	 * approving the appointment Created on: October 9, 2019
	 */
	@RequestMapping(value = "/approveAppointment", method = RequestMethod.POST)
	public String approveAppointment(@RequestParam("appointmentId") String stringAppointmentId, Map<String, Object> model) {
		BigInteger appointmentId = null;
		List<Appointment> appointmentList =null;
		try {
			logger.info("Getting appointment list from the previously selected center id for appointment id validation");
			appointmentList = userService
					.getCenterAppointmentList((BigInteger) session.getAttribute("centerId"));
			
			logger.info("Validating the appointment id entered by the user..");
			appointmentId = userService.validateAppointmentId(stringAppointmentId, appointmentList);
			logger.info("Set appointment Id into session for future use..");
			session.setAttribute("appointmentId", appointmentId);
			model.put("appointmentId", appointmentId);
		} catch (ValidationException exception) {
			logger.error("Caught validation exception in /approveAppointment controller method");
			model.put("appointmentErrorMessage", exception.getMessage());
		}
		logger.info("Returning to approveAppointment page..");
		model.put("appointmentList", appointmentList);
		model.put("centerList", userService.getCenterList());
		return "ApproveAppointment";
	}

	/*
	 * Author: Jayesh Gaur Description: Approves the appointment in the database and
	 * returns the admin to admin home page. Created on: October 9, 2019
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public String approveAppointmentConfirm(Map<String, Object> model) {
		List<DiagnosticCenter> centerList = null;
		try {
			centerList = userService.getCenterList();
			model.put("appointmentList", userService
					.getCenterAppointmentList((BigInteger) session.getAttribute("centerId")));
			model.put("centerList", centerList);
			logger.info("calling service method to approve appointment");
		if (userService.approveAppointment((BigInteger) session.getAttribute("appointmentId"))) {
			logger.info("Approved successfully.. released temporary session variables..");
			session.setAttribute("appointmentId", null);
			session.setAttribute("centerId", null);
			model.put("message", "Approved Successfully");
		}
		}catch(ValidationException exception) {
			logger.error("Caught ValidationException in /approve in controller.. returning the user back to the same page..");
			model.put("appointmentErrorMessage", exception.getMessage());			
			return "ApproveAppointment";
		}
		logger.info("Approved successfully.. returning to admin page");
		return "AdminHome";
	}

	/*
	 * Author: Jayesh Gaur Description: Get View Appointment Page which displays ALL
	 * appointments of the logged in user Created on: October 9, 2019
	 */
	@RequestMapping(value = "viewAppointment", method = RequestMethod.GET)
	public String viewUserAppointments(Map<String, Object> model) {

		List<Appointment> userAppointmentList = userService
				.getAppointmentList((BigInteger) session.getAttribute("userId"));
		model.put("appointmentList", userAppointmentList);
		return "viewUserAppointments";
	}

	/*
	 * Author: Jayesh Gaur Description: Get Excel sheet consisting of appointment
	 * details for the user Created on: October 9, 2019
	 */
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		List<Appointment> appointmentList = userService.getAppointmentList((BigInteger) session.getAttribute("userId"));
		return new ModelAndView((View) new ExcelReportView(), "appointmentList", appointmentList);
	}

}
