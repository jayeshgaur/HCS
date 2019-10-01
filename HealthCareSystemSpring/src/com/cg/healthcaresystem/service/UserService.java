package com.cg.healthcaresystem.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.exception.ValidationException;

public interface UserService {
	public DiagnosticCenter addCenter(DiagnosticCenter center);

	public boolean removeCenter(BigInteger centerId);

	public Test addTest(BigInteger centerId, Test test);

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException;

	public BigInteger register(User user);

	public List<DiagnosticCenter> getCenterList();

	public List<User> getUserList();
	
	public BigInteger userLogin(String email, String password);

	//public boolean setCenterList(List<DiagnosticCenter> centerList);
	
	public String validateContactNo(String userContactNo) throws ValidationException;
	
	public BigInteger validateCenterId(String centerId, List<DiagnosticCenter> centerList)throws UserDefinedException;
	
	public BigInteger validateTestId(String testId,List<Test> testList)throws UserDefinedException;
	
	public BigInteger validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)throws UserDefinedException;
	
	public boolean approveAppointment(BigInteger appointmentId);

	public String validateName(String nextLine) throws ValidationException;
	
	public String validatePassword(String userPassword) throws  ValidationException;
	
	public String validateEmail(String userEmail) throws ValidationException;
	
	public Integer validateAge(Integer age) throws ValidationException;
	
	public String validateGender(String gender) throws ValidationException;
	
	public BigInteger validateUserId(String userId) throws UserDefinedException;

	public LocalDateTime validateDateTime(String dateString) throws UserDefinedException;

//	public LocalTime validateTime(String next)throws UserDefinedException;

	public Appointment addAppointment(Appointment appointment);

	public List<Appointment> getAppointmentList(BigInteger userId);

	public List<Test> getListOfTests(BigInteger centerId);

	public List<Appointment> getCenterAppointmentList(BigInteger centerId);

	public DiagnosticCenter findCenter(BigInteger centerId);
	public Test findTest(BigInteger testId);
	public User findUser(BigInteger userId);
}