package com.cg.healthcaresystemrest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

/*
 * Author: 		Jayesh Gaur
 * Description: Test Cases for Rest Controller functions
 * Created on: 	October 15, 2019
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.healthcaresystemrest.dto.Appointment;
import com.cg.healthcaresystemrest.dto.DiagnosticCenter;
import com.cg.healthcaresystemrest.dto.User;
import com.cg.healthcaresystemrest.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthCareSystemRestApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	UserService userService;
	
	/*
	 * Author:  	Jayesh Gaur
	 * Description: Test case to check register controller.
	 * Input: 		User object properties
	 * Output: 		User object
	 * Created on: 	October 15, 2019
	 */
	
	@Test
	public void registerUser() {
		User user = restTemplate.getForObject("/register", User.class);
		assertThat(user);
	}
	
	
	/*
	 * Author:  	Jayesh Gaur
	 * Description: Test case to check add appointment controller.
	 * Input: 		Appointment object properties
	 * Output: 		Appointment object
	 * Created on: 	October 15, 2019
	 */
	@Test
	public void addAppointment() {
		Appointment appointment = restTemplate.getForObject("/addAppointment", Appointment.class);
		assertThat(appointment);
	}
	
	@Test
	public void getCenters() {
		@SuppressWarnings("unchecked")
		List<DiagnosticCenter> centerList = restTemplate.getForObject("/getCenters", List.class);
		assertThat(centerList);
	}

}
