package com.cg.healthcaresystem.dao;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import java.util.*;

public class UserDaoImpl implements UserDao {
	
	static List<DiagnosticCenter> centerList = new ArrayList<DiagnosticCenter>();
	Map<String, User> userList = new HashMap<String, User>();

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		if(centerList.add(center))
		return center;
		else
			return null;
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
		return centerList;
	}

}
