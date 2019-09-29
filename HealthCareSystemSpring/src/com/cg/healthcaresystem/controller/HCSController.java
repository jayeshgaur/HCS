package com.cg.healthcaresystem.controller;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginpage(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, Map<String, Object> model) {
		if(email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			return "AdminHome";
		}
		else {
			BigInteger userId = userService.userLogin(email, password);
			if(null != userId) {
				
				return "UserHome";
			}
			else {
				model.put("errormessage", "Invalid credentials");
				return "Home";
			}
		}
	}

	@RequestMapping(value = "registerPage", method = RequestMethod.POST)
	public String register() {
		return "AA";
	}

}
