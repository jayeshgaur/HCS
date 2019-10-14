package com.cg.healthcaresystemrest.controller;

import java.math.BigInteger;
import java.util.List;
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

import com.cg.healthcaresystemrest.dto.DiagnosticCenter;
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
	 * Description: 	Returns a list of all centers
	 * Created: 		October 9, 2019 
	 * Input: 			User details in the form of User object 
	 * Output: 			Returns the newly registered user
	 * 					to his homepage and automatically logs him in
	 *
	 */ 
	
	@GetMapping(value="/getCenters")
	public List<DiagnosticCenter> getCenters(){
		List<DiagnosticCenter> centerList = userService.getCenterList();
		return centerList;
	}
	
}
