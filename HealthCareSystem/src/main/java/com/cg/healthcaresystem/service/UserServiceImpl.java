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
	public boolean removeCenter(String centerid) {
		
		return dao.removeCenter(centerid);
	}
	
		
	
	
	public Test addTest(String name,Test test) {
		// TODO Auto-generated method stub
		return dao.addTest(name,test);
		
	}

	public boolean removeTest(String removeCenterId,String removeTestId) {
		// TODO Auto-generated method stub
		return dao.removeTest(removeCenterId,removeTestId);
		
	}

	public void approveAppointment() {
		// TODO Auto-generated method stub
		
	}

	
	public String makeAppointment(User user, DiagnosticCenter center, Test test, String datetime) {
		// TODO Auto-generated method stub
		return null;
	}

	public String register(User user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}
	

	public List<DiagnosticCenter> getCenterList() {
		return dao.getCenterList();
	}
	
	
	public List<User> getUserList() {
		return dao.getUserList();
	}
	
	public boolean setUserList(List<User> li)
	{
		return dao.setUserList(li);
	}
	

}
