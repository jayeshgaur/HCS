package com.cg.healthcaresystem.dao;

import java.util.List;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;

public interface UserDao {
	public DiagnosticCenter addCenter(DiagnosticCenter center);
	public boolean removeCenter(String centerid);
	public Test addTest(String name,Test test);
	public boolean removeTest(String centername,String testname);
	public void approveAppointment();
	public String makeAppointment(User user,DiagnosticCenter center,Test test,String datetime);
	public User register(User user);
	public List<DiagnosticCenter> getCenterList();

}
