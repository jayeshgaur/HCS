package com.cg.healthcaresystem.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;

public interface UserDao {
	public DiagnosticCenter addCenter(DiagnosticCenter center);

	public boolean removeCenter(BigInteger centerid);

	public Test addTest(BigInteger name, Test test);

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId);
	
	public Appointment addAppointment(Appointment appointment);

	public BigInteger register(User user);

	public List<DiagnosticCenter> getCenterList();

	public List<User> getUserList();

	public boolean setUserList(List<User> li);

	public List<Test> getListOfTests(BigInteger centerId);

	public List<Appointment> getAppointmentList(User user);

	//public boolean setCenterList(List<DiagnosticCenter> centerList);
}
