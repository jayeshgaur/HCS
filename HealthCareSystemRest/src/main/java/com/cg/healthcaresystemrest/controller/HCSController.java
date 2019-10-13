package com.cg.healthcaresystemrest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@RestController
public class HCSController {
	
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

}
