package com.cg.healthcaresystem.ui;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.service.UserService;
import com.cg.healthcaresystem.service.UserServiceImpl;



public class MyApp {

	public static void main(String[] args) 
	{
	UserService userService=new UserServiceImpl();
	int userRole=0,adminChoice=0,userChoice=0;
	while(userRole!=3)
	{
	 System.out.println("Enter your role: \n 1. Admin 2. User");
	 Scanner sc = new Scanner(System.in);
	 userRole = sc.nextInt();
	 switch(userRole)
	 {
	  case 1:
		  while(adminChoice!=6)
		  {
			  System.out.println("What function do you want to perform? \n. 1.Add new Center "
			  		+ "2.Remove an existing center \n 3.Add new Test in an existing center "
			  		+ "4.Remove a Test\n 5.Approve appointments");
			  adminChoice = sc.nextInt();
			  switch(adminChoice)
			  {
			  	case 1:
			  		System.out.println("Enter the name of the center:");
			  		String centerName = sc.next();
			  		System.out.println("Enter the address of the center:");
			  		String centerAddress = sc.next();
			  		System.out.println("Enter the contact number of the center:");
			  		BigInteger centerContactNo = sc.nextBigInteger();
			  		
			  		DiagnosticCenter center=new DiagnosticCenter(centerName, centerAddress, centerContactNo);
			  		if(userService.addCenter(center)!=null)
			  		{
			  			System.out.println("Center added successfully!");
			  		}
			  		List<DiagnosticCenter> li = userService.getCenterList();
			  		DiagnosticCenter a = li.get(0);
			  		System.out.println(a);
			  		break;
			  	
			  		
			  	case 2:
			  		System.out.println("Enter the id of center which you want to remove");
			  		String centerid=sc.next();
			  		if(userService.removeCenter(centerid))
			  		{
			  			System.out.println("Center deleted successfully");
			  		}
			  		else
			  		{
			  			System.out.println("No diagnostic center found with centerid "+centerid);
			  		}
			  		
			  		break;
			  	
			  	case 3:
			  		System.out.println("Select the center in which you want to add to test");
			  		List<DiagnosticCenter> centerList=userService.getCenterList();
			  		Iterator itr=centerList.iterator();
			  		int count=1;
			  		while(itr.hasNext())
			  		{
			  			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
			  			System.out.println(count+"."+obj.getCenterName());
			  			count++;
			  			
			  		}
			  		
			  		System.out.println("Enter the center in which you want to add test");
			  		String centername=sc.next();
			  		System.out.println("Enter the test which you want to add in center "+centername);
			  		String testname=sc.next();
			  		Test test=new Test(testname);
			  		if(userService.addTest(centername, test)!=null)
			  		{
			  			System.out.println("Test addedd successfully");
			  		}
			  		else
			  		{
			  			System.out.println("Not added");
			  		}
			  		List<DiagnosticCenter> li2 = userService.getCenterList();
			  		Iterator itr4=li2.iterator();
			  		while(itr4.hasNext())
			  		{
			  			System.out.println(itr4.next());
			  		}
			  		break;
			  	case 4:
			  		//System.out.println("Select the center where you want to delete test");
			  		List<DiagnosticCenter> centerList1=userService.getCenterList();
			  		Iterator itr1=centerList1.iterator();
			  		int counter=1;
			  		while(itr1.hasNext())
			  		{
			  			DiagnosticCenter obj=(DiagnosticCenter) itr1.next();
			  			System.out.println(counter+"."+obj.getCenterName());
			  			counter++;
			  			
			  		}
			  		System.out.println("Enter the center in which you want to remove test");
			  		String centername1=sc.next();
			  		System.out.println("Enter the test which you want to remove");
			  		String testname1=sc.next();
			  		if(userService.removeTest(centername1,testname1))
			  		{
			  			System.out.println("Test deleted successfully");
			  		}
			  		else
			  		{
			  			System.out.println("Test is not present");
			  		}
			  		
			  		
			  		break;
			  	case 6: 
			  		break;
			  	case 7: 
			  		break;
			  	default:
			  		System.out.println("Enter a proper function");
		
			  }
		  }
  		break;
  		
  	 //case 2: 
  		 
//  		 while(userChoice!=3)
//  		 {
//  			System.out.println("What function do u want to perform?\n 1.Registration\n 2.Make Appointments\n 3.Exit");
//	  		 userChoice=sc.nextInt();
//  			 switch(userChoice)
//  			 {
//  			 case 1:
//  				 System.out.println("-------------Registration-----------");
//  				// System.out.println("Enter your userid");
//  				 int userid=counterUserId++;
//  				
//  				 System.out.println("Enter your name");
//				     String name=sc.next();
//  				 System.out.println("Enter your password");
//  				 String password=sc.next();
//  				 try
//  				 {
//  					 UserServiceImpl.validatePassword(password);
//  				 }
//  				 catch(UserDefinedException e)
//  				 {
//  					 System.out.println(e.getMessage());
//  					 break;
//  				 }
//  				 System.out.println("Enter your contactno");
//  				 BigInteger contactno=sc.nextBigInteger();
//  				 try
//  				 {
//  					 UserServiceImpl.validateNumber(contactno);
//  				 }
//  				 catch(UserDefinedException e)
//  				 {
//  					 System.out.println(e.getMessage());
//  					 break;
//  				 }
//  				 System.out.println("Enter your role");
//  				 String role=sc.next();
//  				 User newuser=new User();
//  				 newuser.setUserId(userid);
//  				 newuser.setUserName(name);
//  				 newuser.setUserPassword(password);
//  				 newuser.setContactNo(contactno);
//  				 newuser.setUserRole(role);
//  				 User user=userService.addToRegistrationList(newuser);
//  				 System.out.println(user.getUserName()+" is added .Your registration id is "+user.getUserId());
//  				
//  			
//  				 
//  				 break;
//  			 case 2:
//  				 
//  			 }
//  		 }
//		break;
	 default:
		System.out.println("Enter a proper choice!");
	    break;	
	 }
	}

}

}
