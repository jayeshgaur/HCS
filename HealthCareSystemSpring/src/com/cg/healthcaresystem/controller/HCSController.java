
package com.cg.healthcaresystem.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.exception.ValidationException;
import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginpage() {
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model, HttpSession session) {
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
				return "Login";
			}
		}
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String registerPage(@ModelAttribute("customer") User user) {
		return "Registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("customer") User user, BindingResult bindingResult,
			Map<String, Object> model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "Registration";
		} else {
			BigInteger userId = userService.register(user);
			session.setAttribute("userId", userId);
			model.put("userId", userId);
			return "UserHome";
		}
	}

	@RequestMapping(value = "/addCenterPage", method = RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("Center") DiagnosticCenter center) {
		return "addCenter";
	}

	@RequestMapping(value = "/addCenterSubmit", method = RequestMethod.POST)
	public String addCenter(@Valid @ModelAttribute("Center") DiagnosticCenter center, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("error", "Please try again..");
			return "addCenter";
		} else {
			userService.addCenter(center);
			model.put("message", "Added successfully");
			return "addCenter";
		}
	}

	@RequestMapping(value = "/showAllCenter", method = RequestMethod.GET)
	public ModelAndView getAllData() {
		List<DiagnosticCenter> myList = userService.getCenterList();
		return new ModelAndView("ShowCenters", "data", myList);
	}

	@RequestMapping(value = "/addTestPage", method = RequestMethod.GET)
	public String addTestPage(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "addTest";
	}

	@RequestMapping(value = "/addTestSubmit", method = RequestMethod.POST)
	public String addTestSubmit(@RequestParam("centerId") String sCenterId, @RequestParam("testName") String testName,
			Map<String, Object> model) {
		BigInteger centerId = null;
		try {
			centerId = userService.validateCenterId(sCenterId, userService.getCenterList());
			if (null != userService.addTest(centerId, new Test(testName)))
				model.put("message", "Added successfully");
		} catch (ValidationException exception) {
			model.put("message", exception.getMessage());
		}
		model.put("centerList", userService.getCenterList());
		return "addTest";
	}

	@RequestMapping(value = "/deleteCenterPage", method = RequestMethod.GET)
	public String deleteCenterRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "deleteCenter";
	}

	@RequestMapping(value = "/deleteCenterSubmit", method = RequestMethod.POST)
	public String deleteCenter(@RequestParam("centerId") String sCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		DiagnosticCenter center = null;
		try {
			centerId = userService.validateCenterId(sCenterId, userService.getCenterList());
			center = userService.findCenter(centerId);
			model.put("center", center);
		} catch (ValidationException exception) {
			model.put("centerList", userService.getCenterList());
			model.put("deleteMessage", exception.getMessage());
		}
		return "deleteCenter";

	}

	@RequestMapping(value = "/confirmDeleteCenter", method = RequestMethod.POST)
	public String confirmDeleteCenter(@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {

		if (userService.removeCenter(centerId)) {
			model.put("deleteMessage", "Deleted successfully");
		} else {
			model.put("deleteMessage", "Could not delete, please try again");
		}
		model.put("centerList", userService.getCenterList());
		return "deleteCenter";
	}

	@RequestMapping(value = "/removeTestPage", method = RequestMethod.GET)
	public String deleteTestRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "deleteTest";
	}

	@RequestMapping(value = "/removeTestSelectCenter", method = RequestMethod.POST)
	public String deleteTestSelectCenter(@RequestParam("centerId") String sCenterId, Map<String, Object> model,
			HttpSession session) {
		BigInteger centerId = null;
		try {
			centerId = userService.validateCenterId(sCenterId, userService.getCenterList());
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

	@RequestMapping(value = "/removeTestSelectTest", method = RequestMethod.POST)
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

	@RequestMapping(value = "/removeTestConfirmTest", method = RequestMethod.POST)
	public String deleteTestConfirm(@RequestParam("testId") BigInteger testId,
			@RequestParam("centerId") BigInteger centerId, Map<String, Object> model, HttpSession session) {
		if (userService.removeTest(centerId, testId)) {
			session.setAttribute("testId", null);
			session.setAttribute("centerId", null);
			model.put("message", "Deleted Successfully");
		} else {
			model.put("message", "Error. Please try after some time.");
		}
		return "AdminHome";
	}

	@RequestMapping(value = "/approveAppointmentPage", method = RequestMethod.GET)
	public String approveAppointmentRequest() {
		return "approveAppointment";
	}

	@RequestMapping(value = "/approveAppointmentSubmit", method = RequestMethod.POST)
	public String approveAppointment(@RequestParam("appointmentId") BigInteger appointmentId)
			throws UserDefinedException {
		userService.approveAppointment(appointmentId);
		return "adminHome";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("userRole", null);
		session.setAttribute("userId", null);
		return "Login";
	}

	@RequestMapping(value = "/addAppointmentPage", method = RequestMethod.GET)
	public String addAppointmentRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "ChooseCenter";
	}

	@RequestMapping(value = "/ChooseCenterSubmit", method = RequestMethod.POST)
	public String chooseTestRequest(@RequestParam("centerId") String id, Map<String, Object> model,
			HttpSession session) {
		BigInteger centerId = null;
		try {
			centerId = new BigInteger(id);
		} catch (Exception exception) {

		}

		model.put("testList", userService.getListOfTests(centerId));
		// model.put("center",centerId);
		session.setAttribute("centerId", centerId);

		return "ChooseTest";
    }
	
	
	@RequestMapping(value="/confirmAppointment",method=RequestMethod.POST)
	public String addAppointment(@RequestParam("testId") BigInteger testId,@RequestParam("dateAndTime") String sDateTime,
			@RequestParam("userid") BigInteger userId,	@RequestParam("centerId") BigInteger centerId,Map<String,Object>model)
	{

	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy'T'HH:mm");
	//	LocalDateTime dateTime1 = LocalDateTime.parse(dateTime, formatter);
		Appointment app=new Appointment();
		DiagnosticCenter center=userService.findCenter(centerId);
		Test test=userService.findTest(testId);
		User user=userService.findUser(userId);
		app.setAppointmentstatus(0);
		app.setCenter(center);
		//app.setDateTime(dateTime1);
		// DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		//LocalDateTime dateTime1 = LocalDateTime.parse(dateTime, format);
		LocalDateTime dateTime =null;
		System.out.println(sDateTime);
		//System.out.println("generated LocalDateTime: " + dateTime1);
		try
		{
			dateTime=userService.validateDateTime(sDateTime);
			app.setDateTime(dateTime);
			app.setTest(test);
			app.setUser(user);
			userService.addAppointment(app);
			model.put("message", "Appointment booked successfully");
		}
		catch(UserDefinedException exception)
		{
			model.put("message", exception.getMessage());
			
			return "ChooseTest";
		}
		return "UserHome";
	}

}
