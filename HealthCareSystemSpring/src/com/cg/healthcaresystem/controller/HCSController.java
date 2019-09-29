package com.cg.healthcaresystem.controller;

import java.math.BigInteger;

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
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="loginPage", method=RequestMethod.GET)
	public String loginpage() {
		return "Login";
	}
	
	
	@RequestMapping(value="registerPage", method = RequestMethod.POST)
	public String register() {
		return "AA";
	}
	
	@RequestMapping(value="/addCenterPage", method=RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("Center") DiagnosticCenter center)
	{
		return "addCenter";
	}
	@RequestMapping(value="/addCenterSubmit",method=RequestMethod.POST)
	public String addCenter(@Valid@ModelAttribute("Center") DiagnosticCenter center,
			BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addCenter";
		}
		else
		{
			userService.addCenter(center);
			return "AdminHome";
		}
	}
	
	@RequestMapping(value="/addTestPage", method=RequestMethod.GET)
	public String addTestRequest(@ModelAttribute("mytest") Test test)
	{
		return "addTest";
	}
	@RequestMapping(value="/addTestSubmit",method=RequestMethod.POST)
	public String addTest(@Valid@ModelAttribute("mytest") Test test,@RequestParam("centerId") BigInteger centerId,
			BindingResult result) throws UserDefinedException
	{
		if(result.hasErrors())
		{
			return "addCenter";
		}
		else
		{
			userService.addTest(centerId, test);
			return "adminHome";
		}
	}
	
	@RequestMapping(value="/deleteCenterPage",method=RequestMethod.GET)
	public String deleteCenterRequest()
	{
		return "deleteCenter";
	}
	
	@RequestMapping(value="/deleteCenterSubmit",method=RequestMethod.POST)
	public String deleteCenter(@RequestParam("centerid") BigInteger centerId)
	{
		userService.removeCenter(centerId);
		return "adminHome";
	}

	
	@RequestMapping(value="/deleteTestPage",method=RequestMethod.GET)
	public String deleteTestRequest()
	{
		return "deleteTest";
	}
	
	@RequestMapping(value="/deleteTestSubmit",method=RequestMethod.POST)
	public String deleteTest(@RequestParam("centerid") BigInteger centerId,@RequestParam("testid") BigInteger testId) throws UserDefinedException
	{
		userService.removeTest(centerId, testId);
		return "adminHome";
	}

	@RequestMapping(value="/approveAppointmentPage",method=RequestMethod.GET)
	public String approveAppointmentRequest()
	{
		return "approveAppointment";
	}
	
	@RequestMapping(value="/approveAppointmentSubmit",method=RequestMethod.POST)
	public String approveAppointment(@RequestParam("appointmentId") BigInteger appointmentId) throws UserDefinedException
	{
		userService.approveAppointment(appointmentId);
		return "adminHome";
	}
	


}
