package com.cg.healthcaresystembootmvc.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;
import com.cg.healthcaresystembootmvc.dto.Test;
import com.cg.healthcaresystembootmvc.dto.User;
import com.cg.healthcaresystembootmvc.exception.ExistingCredentialException;
import com.cg.healthcaresystembootmvc.exception.ValidationException;


public interface UserService {
	public DiagnosticCenter addCenter(DiagnosticCenter center);

	public boolean removeCenter(BigInteger centerId) throws ValidationException;

	public Test addTest(BigInteger centerId, Test test);

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws ValidationException;

	public BigInteger register(User user) throws ExistingCredentialException;

	public List<DiagnosticCenter> getCenterList();

	public BigInteger userLogin(String email, String password);

	// public String validateContactNo(String userContactNo) throws ValidationException;
	
	public BigInteger validateCenterId(String centerId, List<DiagnosticCenter> centerList)throws ValidationException;
	
	public BigInteger validateTestId(String testId,List<Test> testList)throws ValidationException;
	
	public BigInteger validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)throws ValidationException;
	
	public boolean approveAppointment(BigInteger appointmentId) throws ValidationException;

	// public String validateName(String nextLine) throws ValidationException;
	
	// public String validatePassword(String userPassword) throws  ValidationException;
	
	// public String validateEmail(String userEmail) throws ValidationException;
	
	// public Integer validateAge(Integer age) throws ValidationException;
	
	// public String validateGender(String gender) throws ValidationException;
	
	public LocalDateTime validateDateTime(String dateString) throws ValidationException;

	public Appointment addAppointment(Appointment appointment);

	public List<Appointment> getAppointmentList(BigInteger userId);

	public List<Test> getListOfTests(BigInteger centerId);

	public List<Appointment> getCenterAppointmentList(BigInteger centerId) throws ValidationException;

	public DiagnosticCenter findCenter(BigInteger centerId);
	public Test findTest(BigInteger testId);
	public User findUser(BigInteger userId);
}