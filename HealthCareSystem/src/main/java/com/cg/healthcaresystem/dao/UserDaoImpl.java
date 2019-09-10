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
	public boolean removeCenter(String centerid) {
		// TODO Auto-generated method stub
		Iterator itr=centerList.iterator();
		while(itr.hasNext())
		{
			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
			if(obj.getCenterId().equals(centerid))
				{
						centerList.remove(obj);
						return true;
				}


		}
		return false;
	}

	
	public Test addTest(String name,Test test) {
		Iterator itr=centerList.iterator();
		int flag=0;
		while(itr.hasNext())
		{
			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
			if(obj.getCenterName().equals(name))
			{
				obj.getListOfTests().add(test);
				flag++;
				
			}
			if(flag>0)
				break;
		}
		if(flag>0)
		return test;
		else
		return null;
		// TODO Auto-generated method stub	
	}

	public boolean removeTest(String centername,String testname) {
		// TODO Auto-generated method stub
		int flag=0;
		Iterator itr=centerList.iterator();
		while(itr.hasNext())
		{
			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
			if(obj.getCenterName().equals(centername))
			{
				List<Test> testList=obj.getListOfTests();
				Iterator testlistitr=testList.iterator();
				while(testlistitr.hasNext())
				{
					Test testobj=(Test)itr.next();
					if(testobj.getTestName()==testname)
					{
						testList.remove(testobj);
						flag++;
						break;
					}
				}
			}
		}
		if(flag==0)
		return false;
		else
			return true;
		
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
