package com.cg.healthcaresystem.controller;

import java.math.BigInteger;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;

import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginpage() {
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model) {
		if (email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			return "AdminHome";
		} else {
			BigInteger userId = userService.userLogin(email, password);
			if (null != userId) {

				return "UserHome";
			} else {
				model.put("errormessage", "Invalid credentials");
				return "Login";
			}
		}
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String register() {
		return "Registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@ModelAttribute("customer") User user, BindingResult bindingResult) {
		return "Registration";
	}

	@RequestMapping(value = "/addCenterPage", method = RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("mycenter") DiagnosticCenter center) {
		return "addCenter";
	}

	@RequestMapping(value = "/addCenterSubmit", method = RequestMethod.POST)
	public String addCenter(@Valid @ModelAttribute("mycenter") DiagnosticCenter center, BindingResult result) {
		if (result.hasErrors()) {
			return "addCenter";
		} else {
			userService.addCenter(center);
			return "adminHome";
		}
	}

	@RequestMapping(value = "/addTestPage", method = RequestMethod.GET)
	public String addTestRequest(@ModelAttribute("mytest") Test test) {
		return "addTest";
	}

	@RequestMapping(value = "/addTestSubmit", method = RequestMethod.POST)
	public String addTest(@Valid @ModelAttribute("mytest") Test test, @RequestParam("centerId") BigInteger centerId,
			BindingResult result) throws UserDefinedException {
		if (result.hasErrors()) {
			return "addCenter";
		} else {
			userService.addTest(centerId, test);
			return "adminHome";
		}
	}

	@RequestMapping(value = "/deleteCenterPage", method = RequestMethod.GET)
	public String deleteCenterRequest() {
		return "deleteCenter";
	}

	@RequestMapping(value = "/deleteCenterSubmit", method = RequestMethod.POST)
	public String deleteCenter(@RequestParam("centerid") BigInteger centerId) {
		userService.removeCenter(centerId);
		return "adminHome";
	}

	@RequestMapping(value = "/deleteTestPage", method = RequestMethod.GET)
	public String deleteTestRequest() {
		return "deleteTest";
	}

	@RequestMapping(value = "/deleteTestSubmit", method = RequestMethod.POST)
	public String deleteTest(@RequestParam("centerid") BigInteger centerId, @RequestParam("testid") BigInteger testId)
			throws UserDefinedException {
		userService.removeTest(centerId, testId);
		return "adminHome";
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

}
