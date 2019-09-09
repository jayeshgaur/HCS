package com.cg.healthcaresystem.dao;

import java.util.List;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;

public interface UserDao {
	public DiagnosticCenter addCenter(DiagnosticCenter center);
	public void removeCenter(DiagnosticCenter center);
	public void addTest(Test test);
	public void removeTest(Test test);
	public void approveAppointment();
	public String makeAppointment(User user,DiagnosticCenter center,Test test,String datetime);
	public User register(User user);
	public List<DiagnosticCenter> getCenterList();

}
