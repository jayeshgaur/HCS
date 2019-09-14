package com.cg.healthcaresystem.service;


import java.time.LocalDateTime;

import java.util.List;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;

public interface UserService {
	public DiagnosticCenter addCenter(DiagnosticCenter center);

	public boolean removeCenter(String centerId);

	public Test addTest(String name, Test test) throws UserDefinedException;

	public boolean removeTest(String removeCenterId, String removeTestId, List<DiagnosticCenter> centerList);

	public String register(User user);

	public List<DiagnosticCenter> getCenterList();

	public List<User> getUserList();

	public boolean setCenterList(List<DiagnosticCenter> centerList);
	
	public String validateContactNo(String userContactNo) throws UserDefinedException;
	
	public String validateCenterId(String centerId, List<DiagnosticCenter> centerList)throws UserDefinedException;
	
	public String validateTestId(String removeTestId, String centerId, List<DiagnosticCenter> centerList)throws UserDefinedException;
	
	public String validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)throws UserDefinedException;
	
	public boolean approveAppointment(String appointmentId, List<Appointment> appointmentList);

	public String validateName(String nextLine) throws UserDefinedException;
	
	public String validatePassword(String userPassword) throws UserDefinedException;
	
	public String validateEmail(String userEmail) throws UserDefinedException;
	
	public Integer validateAge(Integer age) throws UserDefinedException;
	
	public String validateGender(String gender) throws UserDefinedException;
	
	public User validateUserId(String userId) throws UserDefinedException;

	public LocalDateTime validateDateTime(String dateString) throws UserDefinedException;

//	public LocalTime validateTime(String next)throws UserDefinedException;

	public Appointment addAppointment(Appointment appointment, String centerId, List<DiagnosticCenter> centerList);

	public List<Appointment> getAppointmentList(User user);

}
