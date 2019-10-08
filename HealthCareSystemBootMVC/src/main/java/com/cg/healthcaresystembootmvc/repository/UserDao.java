package com.cg.healthcaresystembootmvc.repository;

import java.math.BigInteger;
import java.util.List;

import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;
import com.cg.healthcaresystembootmvc.dto.Test;
import com.cg.healthcaresystembootmvc.dto.User;



public interface UserDao {
	public DiagnosticCenter addCenter(DiagnosticCenter center);

	public boolean removeCenter(BigInteger centerid);

	public Test addTest(BigInteger centerId, Test test);

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId);
	
	public Appointment addAppointment(Appointment appointment);

	public BigInteger register(User user);

	public List<DiagnosticCenter> getCenterList();

	public List<User> getUserList();

	public List<Test> getListOfTests(BigInteger centerId);

	public List<Appointment> getAppointmentList(BigInteger userId);
	
	public boolean approveAppointment(BigInteger appointmentId);
	
	public DiagnosticCenter findCenter(BigInteger centerId);
	
	public User findUser(BigInteger userId);
	
	public Test findTest(BigInteger testId);

	public List<Appointment> getCenterAppointmentList(BigInteger centerId);
	
	public BigInteger getUserLogin(String email, String password);

	//public boolean setCenterList(List<DiagnosticCenter> centerList);
}