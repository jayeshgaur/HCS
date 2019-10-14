package com.cg.healthcaresystemrest.controller;

import java.math.BigInteger;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcaresystemrest.dto.User;
import com.cg.healthcaresystemrest.dto.UserDetailsImpl;
import com.cg.healthcaresystemrest.exception.ExistingCredentialException;
import com.cg.healthcaresystemrest.service.UserServiceImpl;

@ComponentScan
@RestController
public class HCSController {
	
	@Autowired
	private UserServiceImpl userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HCSController.class);
	
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
	 * Description: 	Registers the new user into the system if
	 * 					all the validation tests are passed 
	 * Created: 		October 9, 2019 
	 * Input: 			User details in the form of User object 
	 * Output: 			Returns the newly registered user
	 * 					to his homepage and automatically logs him in
	 */ 
	// @PostMapping(value = "/register")
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
				user.setUserRole("ROLE_Customer");
				userId = userService.register(user);
				// Log the user in and set his user ID into the session object and send him to
				// user home page
				logger.info("Registration successful.. logging the user in");
			} catch (ExistingCredentialException exception) {
				// if registration failed, return back to registration page with proper error
				// message
				logger.error("Caught ExistingCredentialException in register post, returning to Registration page");
				return "Registration";
			}
			logger.info("New registered user logged in, returning the user id");
			return userId.toString();
		}
	}


}
