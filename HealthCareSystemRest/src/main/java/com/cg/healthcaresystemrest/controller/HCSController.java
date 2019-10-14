package com.cg.healthcaresystemrest.controller;

/*
 * Author: Jayesh Gaur
 * Description: Controller class for all functionalities
 * Created on: October 14, 2019
 */
import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.healthcaresystemrest.dto.DiagnosticCenter;
import com.cg.healthcaresystemrest.dto.Test;
import com.cg.healthcaresystemrest.exception.ValidationException;
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
	 * Created on: 		October 9, 2019 
	 * Input: 			NULL
	 * Output: 			A list of all center objects
	 */ 
	@GetMapping(value="/getCenters")
	public ResponseEntity<List<DiagnosticCenter>> getCenters(){
		logger.info("Getting list of all centers in the system..");
		List<DiagnosticCenter> centerList = userService.getCenterList();
		if(centerList.size() == 0) {
			logger.info("No centers present in the system. Returning NO_CONTENT");
			return new ResponseEntity<List<DiagnosticCenter>>(HttpStatus.NO_CONTENT);
		}else {
			logger.info("Returning center list..");
			return new ResponseEntity<List<DiagnosticCenter>>(centerList,HttpStatus.OK);
		}
		}
	
	/*
	 * Author: 			Jayesh Gaur 
	 * Description: 	Returns a list of all tests in the center corresponding to the center Id
	 * Created on: 		October 9, 2019 
	 * Input: 			Center Id of the center you want to retrieve tests of
	 * Output: 			A list of all test objects in the center object corresponding to the
	 * 						center Id entered
	 */ 
	@GetMapping(value="/getTests")
	public ResponseEntity<List<Test>> getTests(@RequestParam("centerId") String stringCenterId){
		BigInteger centerId=null;
		List<Test> testList = null;
		try {
			logger.info("Validating center id entered as parameter..");
			centerId = userService.validateCenterId(stringCenterId, userService.getCenterList());
			logger.info("Center Id validated... Getting test List");
			testList = userService.getListOfTests(centerId);
			if (testList.size() > 0) {
				System.out.println(testList);
				logger.info("Returning test list in the center..");
				return new ResponseEntity<List<Test>>(testList, HttpStatus.OK);
			} else {
				logger.info("Test list is empty.");
				return new ResponseEntity<List<Test>>(HttpStatus.NO_CONTENT);
			}
		} catch (ValidationException exception) {
			logger.error("Caught Validation Exception... ");
			return new ResponseEntity<List<Test>>(HttpStatus.BAD_REQUEST);
		}	
	}
}
