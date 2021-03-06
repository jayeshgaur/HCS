
package com.cg.healthcaresystem.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
import com.cg.healthcaresystem.exceldownload.ExcelReportView;
import com.cg.healthcaresystem.exception.ValidationException;
import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

//	@RequestMapping(value = "**", method = RequestMethod.GET)
//	public String defaultMapper() {
//		return "Home.jsp";
//	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginpage() {
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model) {
		if (email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			session.setAttribute("userRole", "admin");
			return "redirect:/AdminHome.jsp";
		} else {
			BigInteger userId = userService.userLogin(email, password);
			if (null != userId) {
				session.setAttribute("userId", userId);
				return "redirect:/UserHome.jsp";
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
			Map<String, Object> model) {
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
			return "AdminHome";
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
	public String deleteTestSelectCenter(@RequestParam("centerId") String sCenterId, Map<String, Object> model) {
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
			@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {
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

	@RequestMapping(value = "/addAppointmentPage", method = RequestMethod.GET)
	public String addAppointmentRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "ChooseCenter";
	}

	@RequestMapping(value = "/ChooseCenterSubmit", method = RequestMethod.POST)
	public String chooseTestRequest(@RequestParam("centerId") String sCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		List<Test> testList;
		try {
			centerId = userService.validateCenterId(sCenterId, userService.getCenterList());
			testList = userService.getListOfTests(centerId);
			if (testList.size() > 0) {
				model.put("testList", testList);
				session.setAttribute("centerId", centerId);
			} else {
				model.put("centerList", userService.getCenterList());
				model.put("message", "Sorry, no tests present in that center.");
				return "ChooseCenter";
			}
		} catch (ValidationException exception) {
			model.put("centerList", userService.getCenterList());
			model.put("message", exception.getMessage());
			return "ChooseCenter";
		}
		return "ChooseTest";
	}

	@RequestMapping(value = "/confirmAppointment", method = RequestMethod.POST)
	public String addAppointment(@RequestParam("testId") String sTestId, @RequestParam("dateAndTime") String sDateTime,
			@RequestParam("userId") BigInteger userId, @RequestParam("centerId") BigInteger centerId,
			Map<String, Object> model) {
		Appointment appointment = new Appointment();
		DiagnosticCenter center = userService.findCenter(centerId);
		LocalDateTime dateTime;
		BigInteger testId;
		List<Test> testList = userService.getListOfTests((BigInteger) session.getAttribute("centerId"));
		try {
			testId = userService.validateTestId(sTestId, testList);
			dateTime = userService.validateDateTime(sDateTime);
			Test test = userService.findTest(testId);
			User user = userService.findUser(userId);
			appointment.setAppointmentStatus(0);
			appointment.setCenter(center);
			System.out.println(sDateTime);
			appointment.setDateTime(dateTime);
			appointment.setTest(test);
			appointment.setUser(user);
			userService.addAppointment(appointment);
			model.put("message", "Appointment booked successfully");
		} catch (ValidationException exception) {
			model.put("testList", testList);
			model.put("message", exception.getMessage());
			return "ChooseTest";
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

	@RequestMapping(value = "viewAppointmentPage", method = RequestMethod.GET)
	public String viewUserAppointments(Map<String, Object> model) {
		List<Appointment> userAppointmentList = userService
				.getAppointmentList((BigInteger) session.getAttribute("userId"));
		model.put("appointmentList", userAppointmentList);
		return "viewUserAppointments";
	}

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel() {
		List<Appointment> appointmentList = userService.getAppointmentList((BigInteger) session.getAttribute("userId"));
		return new ModelAndView(new ExcelReportView(), "appointmentList", appointmentList);
	}

}
