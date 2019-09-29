package com.cg.healthcaresystem.controller;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

}
