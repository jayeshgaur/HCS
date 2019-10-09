package com.cg.healthcaresystembootmvc.controller;

/*
 * author: Jayesh Gaur, Nidhi
 */

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.cg.healthcaresystembootmvc.exception.ValidationException;
import com.cg.healthcaresystembootmvc.service.UserService;

@ComponentScan
@Controller
public class HCSController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultMapper() {
		return "Home";
	}

	/*
	 * Author: Jayesh Gaur Description: Map /Home to Home.jsp Created: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public String HomeMapper() {
		return "Home";
	}

	/*
	 * Author: Jayesh Gaur Description: Get login page Created: October 9, 2019
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginpage() {
		return "Login";
	}

	/*
	 * Author: Jayesh Gaur Description: Get Admin Home page Created: October 9, 2019
	 */
	@RequestMapping(value = "/AdminHome", method = RequestMethod.GET)
	public String adminHomePage() {
		return "AdminHome";
	}

	/*
	 * Author: Jayesh Gaur Description: Get User Home page Created: October 9, 2019
	 */
	@RequestMapping(value = "/UserHome", method = RequestMethod.GET)
	public String userHomePage() {
		return "UserHome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model) {
		if (email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			session.setAttribute("userRole", "admin");
			return "AdminHome";
		} else {
			BigInteger userId = userService.userLogin(email, password);
			if (null != userId) {
				session.setAttribute("userId", userId);
				return "UserHome";
			} else {
				model.put("errormessage", "Invalid credentials");
				return "login";
			}
		}
	}

	/*
	 * Author: Jayesh Gaur Description: Get registration page Created: October 9,
	 * 2019
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(@ModelAttribute("customer") User user) {
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

		// Check if the validation tests are passed. Return the user back to
		// registration page if any test fails
		if (bindingResult.hasErrors()) {
			return "Registration";
		} else {

			// Register the user and get his automatically generated user Id
			BigInteger userId = userService.register(user);

			// Log the user in and set his user ID into the session object
			session.setAttribute("userId", userId);
			model.put("userId", userId);
			return "UserHome";
		}
	}
	
	

	@RequestMapping(value = "/Center/Add", method = RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("Center") DiagnosticCenter center) {
		return "addCenter";
	}

	// Add Center
	@RequestMapping(value = "/Center/Add", method = RequestMethod.POST)
	public String addCenter(@Valid @ModelAttribute("Center") DiagnosticCenter center, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("error", "Please try again..");
			return "addCenter";
		} else {
			userService.addCenter(center);
			model.put("message", "Added successfully");
			return "AdminHome";
		}
	}

	@RequestMapping(value = "/showAllCenter", method = RequestMethod.GET)
	public ModelAndView getAllData() {
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

	@RequestMapping(value = "/Center/Delete", method = RequestMethod.GET)
	public String deleteCenterRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "deleteCenter";
	}

	@RequestMapping(value = "/Center/Delete", method = RequestMethod.POST)
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

	@RequestMapping(value = "/Center/Delete/Confirm", method = RequestMethod.POST)
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

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		session.setAttribute("userRole", null);
		session.setAttribute("userId", null);
		return "Login";
	}

	/*
	 * Author: Jayesh Gaur Description: Get Add Appointment Page for the user
	 * Created on: October 9, 2019
	 */
	@RequestMapping(value = "/addAppointment", method = RequestMethod.GET)
	public String addAppointment(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
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
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());

			// Get list of tests under the center corresponding to the center Id.
			testList = userService.getListOfTests(centerId);

			// Return the test list if tests are present
			if (testList.size() > 0) {
				model.put("testList", testList);
				session.setAttribute("centerId", centerId);
			} else {
				// Return the centerList again to the user and redirect to the previous page if no tests exist in the center
				model.put("message", "Sorry, no tests present in that center.");
			}
		} catch (ValidationException exception) {
			// Redirect to the same page if the center Id is not valid and ask the user to select a different center or select a proper center Id
			model.put("message", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
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

	@RequestMapping(value = "/approveAppointmentPage", method = RequestMethod.GET)
	public String approveAppointment(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "ApproveAppointment";
	}

	@RequestMapping(value = "/approveAppointmentSelectCenter", method = RequestMethod.POST)
	public String approveAppointmentSelectCenter(@RequestParam("centerId") String sCenterId,
			Map<String, Object> model) {
		BigInteger centerId = null;
		try {
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

	@RequestMapping(value = "/approveAppointmentSelectAppointment", method = RequestMethod.POST)
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

	@RequestMapping(value = "/approveAppointmentConfirmAppointment", method = RequestMethod.POST)
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

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		List<Appointment> appointmentList = userService.getAppointmentList((BigInteger) session.getAttribute("userId"));
		return new ModelAndView((View) new ExcelReportView(), "appointmentList", appointmentList);
	}

}
