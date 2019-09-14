package com.cg.healthcaresystem.dao;

import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.User;
import java.util.*;

public class UserDaoImpl implements UserDao {

	private static List<DiagnosticCenter> centerList = new ArrayList<DiagnosticCenter>();
	List<User> userList = new ArrayList<User>();

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		if (centerList.add(center))
			return center;
		else
			return null;
	}

	public boolean removeCenter(String centerId) {
		DiagnosticCenter diagnosticCenter=null;
		Iterator<DiagnosticCenter> iterator = centerList.iterator();
		while (iterator.hasNext()) {
			diagnosticCenter = iterator.next();
			if (diagnosticCenter.getCenterId().equals(centerId)) {
				break;
//				centerList.remove(diagnosticCenter);
//				status =  true;
			}

		}
		return (centerList.remove(diagnosticCenter));
	}

//	public Test addTest(String centerId, Test test) {
//		for (DiagnosticCenter diagnosticCenter : centerList) {
//			if(diagnosticCenter.getCenterId().equals(centerId))
//			{
//				diagnosticCenter.addTest(test);
//				return test;
//			}
//		}
//		return null;
//	}

//	public boolean removeTest(String removeCenterId, String removeTestId) {
//		List<Test> tempTestList = new ArrayList<Test>();
//		for (int i = 0; i < centerList.size(); i++) {
//			if (centerList.get(i).getCenterId().equals(removeCenterId)) {
//				tempTestList = centerList.get(i).getListOfTests();
//				for (int j = 0; j < tempTestList.size(); j++) {
//					if (tempTestList.get(j).getTestId().equals(removeTestId)) {
//						tempTestList.remove(j);
//					}
//				}
//				centerList.get(i).setListOfTests(tempTestList);
//				return true;
//			}
//		}
//		return false;
//
//		// int flag=0;
////		Iterator itr=centerList.iterator();
////		while(itr.hasNext())
////		{
////			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
////			if(obj.getCenterName().equals(centername))
////			{
////				List<Test> testList=obj.getListOfTests();
////				Iterator testlistitr=testList.iterator();
////				while(testlistitr.hasNext())
////				{
////					Test testobj=(Test)itr.next();
////					if(testobj.getTestName()==testname)
////					{
////						testList.remove(testobj);
////						flag++;
////						break;
////					}
////				}
////			}
////		}
////		if(flag==0)
////		return false;
////		else
////			return true;
//
//	}

	public List<DiagnosticCenter> getCenterList() {
		return centerList;
	}

	public boolean setCenterList(List<DiagnosticCenter> centerList) {
		return (null != (UserDaoImpl.centerList = centerList));
	}

	// RegisteredUserList
	public List<User> getUserList() {
		return userList;
	}

	public boolean setUserList(List<User> userList) {
		this.userList = userList;
		return true;
	}

	public String register(User user) {
		userList.add(user);
		return user.getUserId();
	}

}
