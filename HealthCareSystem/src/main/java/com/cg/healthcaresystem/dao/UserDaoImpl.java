package com.cg.healthcaresystem.dao;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import java.util.*;

public class UserDaoImpl implements UserDao {
	
	static List<DiagnosticCenter> centerList = new ArrayList<DiagnosticCenter>();
	List<User> userList = new ArrayList<User>();

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
		for(int i=0;i<centerList.size();i++)
		{
			if(centerList.get(i).getCenterName().equals(name))
			{
				centerList.get(i).addTest(test);
			}
		}
		return test;
	}

	public boolean removeTest(String removeCenterId,String removeTestId) {
		// TODO Auto-generated method stub
		List<Test> tempTestList = new ArrayList<Test>();
		for(int i=0;i<centerList.size();i++)
		{
			if(centerList.get(i).getCenterId().equals(removeCenterId))
			{
				tempTestList = centerList.get(i).getListOfTests();
				for(int j =0;j<tempTestList.size();j++)
				{
					if(tempTestList.get(j).getTestId().equals(removeTestId))
					{
						tempTestList.remove(j);
					}
				}
				centerList.get(i).setListOfTests(tempTestList);
				return true;
			}
		}
		return false;
		
		//		int flag=0;
//		Iterator itr=centerList.iterator();
//		while(itr.hasNext())
//		{
//			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
//			if(obj.getCenterName().equals(centername))
//			{
//				List<Test> testList=obj.getListOfTests();
//				Iterator testlistitr=testList.iterator();
//				while(testlistitr.hasNext())
//				{
//					Test testobj=(Test)itr.next();
//					if(testobj.getTestName()==testname)
//					{
//						testList.remove(testobj);
//						flag++;
//						break;
//					}
//				}
//			}
//		}
//		if(flag==0)
//		return false;
//		else
//			return true;
		
	}

	public void approveAppointment() {
		// TODO Auto-generated method stub
		
	}

	public String makeAppointment(User user, DiagnosticCenter center, Test test, String datetime) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DiagnosticCenter> getCenterList() {
		return centerList;
	}
	
	public boolean setCenterList(List<DiagnosticCenter> centerList)
	{
		this.centerList = centerList;
		return true;
	}
	
	
	//RegisteredUserList
	public List<User> getUserList() {
		return userList;
	}
	
	public boolean setUserList(List<User> userList) {
		 this.userList = userList;
		 return true;
	}
	
	public String register(User user) {
		// TODO Auto-generated method stub
		userList.add(user);
		return user.getUserId();
	}


	
}
