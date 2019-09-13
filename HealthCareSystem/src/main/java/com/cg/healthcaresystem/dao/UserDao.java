package com.cg.healthcaresystem.dao;

import java.util.List;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;

public interface UserDao {
	public DiagnosticCenter addCenter(DiagnosticCenter center);

	public boolean removeCenter(String centerid);

	public Test addTest(String name, Test test);

//	public boolean removeTest(String removeCenterid, String removeTestId);

	public String register(User user);

	public List<DiagnosticCenter> getCenterList();

	public List<User> getUserList();

	public boolean setUserList(List<User> li);

	public boolean setCenterList(List<DiagnosticCenter> centerList);
}
