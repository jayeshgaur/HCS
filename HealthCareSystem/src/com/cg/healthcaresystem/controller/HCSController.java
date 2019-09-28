package com.cg.healthcaresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {
	
	@Autowired
	private UserService userService;

}
