package com.cg.healthcaresystemrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcaresystemrest.dto.UserDetailsImpl;
import com.cg.healthcaresystemrest.service.UserServiceImpl;

@ComponentScan
@RestController
public class HCSController {
	
	private static final Logger logger = LoggerFactory.getLogger(HCSController.class);
	
	@Autowired
	private UserServiceImpl userService;

	
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
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDetailsImpl user) throws Exception {
		return ResponseEntity.ok(userService.register(user));
	}

}
