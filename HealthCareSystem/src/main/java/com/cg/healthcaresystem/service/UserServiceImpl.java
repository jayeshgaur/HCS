package com.cg.healthcaresystem.service;

import java.util.List;

import com.cg.healthcaresystem.dao.UserDao;
import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;

public class UserServiceImpl implements UserService {
	
	UserDao dao = new UserDaoImpl();

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		return dao.addCenter(center);
	}

	public void removeCenter(DiagnosticCenter center) {
		// TODO Auto-generated method stub
		
	}
	
	public void addTest(Test test) {
		// TODO Auto-generated method stub
		
	}

	public void removeTest(Test test) {
		// TODO Auto-generated method stub
		
	}

	public void approveAppointment() {
		// TODO Auto-generated method stub
		
	}

	
	public String makeAppointment(User user, DiagnosticCenter center, Test test, String datetime) {
		// TODO Auto-generated method stub
		return null;
	}

	public User register(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public List<DiagnosticCenter> getCenterList() {
		return dao.getCenterList();
	}

}
