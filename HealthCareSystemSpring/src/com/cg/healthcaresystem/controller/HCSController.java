
package com.cg.healthcaresystem.controller;

import java.math.BigInteger;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.service.UserService;

@Controller
public class HCSController {

	@Autowired
	HttpSession session;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginpage() {
		return "Login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			Map<String, Object> model, HttpSession session) {
		if (email.equals("admin@hcs.com") && password.equals("hcsadmin")) {
			session.setAttribute("userRole", "admin");
			return "AdminHome";
		} else {
			BigInteger userId = userService.userLogin(email, password);
			if (null != userId) {
				session.setAttribute("userId", userId);
				return "UserHome";
			} else {
				model.put("errormessage", "Invalid credentials");
				return "Login";
			}
		}
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String registerPage(@ModelAttribute("customer") User user) {
		return "Registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("customer") User user, BindingResult bindingResult,
			Map<String, Object> model) {
		if (bindingResult.hasErrors()) {
			return "Registration";
		} else {
			BigInteger userId = userService.register(user);
			model.put("userId", userId);
			return "UserHome";
		}
	}

	@RequestMapping(value = "/addCenterPage", method = RequestMethod.GET)
	public String addCenterRequest(@ModelAttribute("Center") DiagnosticCenter center) {
		return "addCenter";
	}

	@RequestMapping(value = "/addCenterSubmit", method = RequestMethod.POST)
	public String addCenter(@Valid @ModelAttribute("Center") DiagnosticCenter center, BindingResult result,
			Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("error", "Please try again..");
			return "addCenter";
		} else {
			userService.addCenter(center);
			model.put("message", "Added successfully");
			return "addCenter";
		}
	}

	@RequestMapping(value = "/showAllCenter", method = RequestMethod.GET)
	public ModelAndView getAllData() {
		List<DiagnosticCenter> myList = userService.getCenterList();
		return new ModelAndView("ShowCenters", "data", myList);
	}

	@RequestMapping(value = "/addTestPage", method = RequestMethod.GET)
	public String addTestRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "addTest";
	}

	@RequestMapping(value = "/addTestSubmit", method = RequestMethod.POST)
	public String addTestRequest(@RequestParam("centerId") String sCenterId,
			@RequestParam("testName") String testName, Map<String, Object> model) {
		BigInteger centerId = null;
		try{
			centerId = new BigInteger(sCenterId);
		}catch(Exception exception) {
			centerId = null;
		}
		model.put("centerList", userService.getCenterList());
		if (null != userService.addTest(centerId, new Test(testName))) {
			model.put("message", "Added successfully");
		} else {
			model.put("message", "Please try again..");
		}
		return "addTest";
	}

	/*
	 * @RequestMapping(value="/addTestPage", method=RequestMethod.GET) public String
	 * addTestRequest(@ModelAttribute("test") Test test,Map<String,Object> model) {
	 * List<DiagnosticCenter> centerList=userService.getCenterList(); List<String>
	 * centerName=new ArrayList<String>(); for(int i=0;i<centerList.size();i++) {
	 * centerName.add(centerList.get(i).getCenterName()); }
	 * model.put("centerName",centerName); return "addTest"; }
	 * 
	 * @RequestMapping(value="/addTestSubmit",method=RequestMethod.POST) public
	 * String addTest(@Valid@ModelAttribute("test") Test
	 * test,@RequestParam("centerId") BigInteger centerId, BindingResult
	 * result,Map<String,Object> model) throws UserDefinedException {
	 * if(result.hasErrors()) { return "addTest"; } else {
	 * userService.addTest(centerId, test);
	 * //model.put("centerList",userService.getListOfTests(centerId));
	 * 
	 * return "AdminHome"; } }
	 */

	@RequestMapping(value = "/deleteCenterPage", method = RequestMethod.GET)
	public String deleteCenterRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "deleteCenter";
	}

	@RequestMapping(value = "/deleteCenterSubmit", method = RequestMethod.POST)
	public String deleteCenter(@RequestParam("centerId") String sCenterId, Map<String, Object> model) {
		BigInteger centerId = null;
		DiagnosticCenter center=null;
		try {
			centerId = new BigInteger(sCenterId);
		} catch (Exception exception) {
			centerId = null;
		}
		if (centerId != null) {
			center = userService.findCenter(centerId);
		}
		if (!center.isDeleted()) {
			model.put("center", center);
		} else {
			model.put("centerList", userService.getCenterList());
			model.put("deleteMessage", "Invalid Center Id");
		}
		return "deleteCenter";

	}

	@RequestMapping(value = "/confirmDeleteCenter", method = RequestMethod.POST)
	public String confirmDeleteCenter(@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {
		if (userService.removeCenter(centerId)) {
			model.put("deleteMessage", "Deleted successfully");
		} else {
			model.put("deleteMessage", "Could not delete, please try again");
		}
		return "deleteCenter";
	}

	@RequestMapping(value = "/removeTestPage", method = RequestMethod.GET)
	public String deleteTestRequest(Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		return "removeTest";
	}

	@RequestMapping(value = "/removeTestSelectCenter", method = RequestMethod.POST)
	public String deleteTestSelectCenter(@RequestParam("centerId") BigInteger centerId, Map<String, Object> model) {
		model.put("centerList", userService.getCenterList());
		model.put("testList", userService.getListOfTests(centerId));
		return "removeTest";
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

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("userRole", null);
		session.setAttribute("userId", null);
		return "Login";
	}

}
