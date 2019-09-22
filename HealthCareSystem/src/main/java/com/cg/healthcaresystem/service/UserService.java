package com.cg.healthcaresystem.service;


import java.math.BigInteger;
import java.time.LocalDateTime;

import java.util.List;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;

public interface UserService {
	public DiagnosticCenter addCenter(DiagnosticCenter center) throws UserDefinedException;

	public boolean removeCenter(BigInteger centerId);

	public Test addTest(BigInteger centerid, Test test) throws UserDefinedException;

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException;

	public BigInteger register(User user);

	public List<DiagnosticCenter> getCenterList();

	public List<User> getUserList();

	//public boolean setCenterList(List<DiagnosticCenter> centerList);
	
	public String validateContactNo(String userContactNo) throws UserDefinedException;
	
	public BigInteger validateCenterId(String centerId, List<DiagnosticCenter> centerList)throws UserDefinedException;
	
	public BigInteger validateTestId(String testId,List<Test> testList)throws UserDefinedException;
	
	public BigInteger validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)throws UserDefinedException;
	
	public boolean approveAppointment(BigInteger appointmentId);

	public String validateName(String nextLine) throws UserDefinedException;
	
	public String validatePassword(String userPassword) throws UserDefinedException;
	
	public String validateEmail(String userEmail) throws UserDefinedException;
	
	public Integer validateAge(Integer age) throws UserDefinedException;
	
	public String validateGender(String gender) throws UserDefinedException;
	
	public BigInteger validateUserId(String userId) throws UserDefinedException;

	public LocalDateTime validateDateTime(String dateString) throws UserDefinedException;

//	public LocalTime validateTime(String next)throws UserDefinedException;

	public Appointment addAppointment(Appointment appointment);

	public List getAppointmentList(BigInteger userId);

	public List<Test> getListOfTests(BigInteger centerId);

	public List<Appointment> getListOfAppointments();
}
