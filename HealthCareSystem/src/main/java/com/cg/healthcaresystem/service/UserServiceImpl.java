package com.cg.healthcaresystem.service;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.healthcaresystem.dao.UserDao;
import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;

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
	
	public boolean setCenterList(List<DiagnosticCenter> centerList)
	{
		return dao.setCenterList(centerList);
	}
	
	
	public List<User> getUserList() {
		return dao.getUserList();
	}
	
	public boolean setUserList(List<User> li)
	{
		return dao.setUserList(li);
	}
	
	
	public static void validatePassword(String userPassword) throws UserDefinedException {
		// TODO Auto-generated method stub
		String regex="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userPassword);
		if(matcher.matches()==false)
		{
			throw new UserDefinedException("Enter valid password.\n Password should must contain one number,one capital letter,one small letter and one special character");
		}
		
		
	}
	public static void validateName(String userName) throws UserDefinedException {
		// TODO Auto-generated method stub
		String regex="^[A-Z].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userName);
		if(matcher.matches()==false)
		{
			throw new UserDefinedException("Enter valid name.\n Name should start with capital letter");
		}
		
	}
	public static void validateContactNo(BigInteger userContactNo) throws UserDefinedException {
		// TODO Auto-generated method stub
		if(userContactNo.toString().length()!=10)
		{
			throw new UserDefinedException("Enter valid contact number.Phone no size should be 10");
		}
		
	}
	public static void validateEmail(String userEmail) throws UserDefinedException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userEmail);
		if(matcher.matches()==false)
		{
			throw new UserDefinedException("Enter valid email id.");
		}
		
		 
		
		
	}
	public static void validateAge(Integer age) throws UserDefinedException {
		// TODO Auto-generated method stub
		if(age<0 && age>110)
		{
			throw new UserDefinedException("Enter valid age");
		}
		
	}
	public static void validateGender(String gender) throws UserDefinedException {
		// TODO Auto-generated method stub
		if(!(gender.equals('M') || gender.equals('F') || gender.equals('O')))
		{
			throw new UserDefinedException("Enter valid gender");
		}
		
	}
	

}
