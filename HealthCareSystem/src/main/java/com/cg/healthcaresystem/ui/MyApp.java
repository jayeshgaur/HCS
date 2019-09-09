package com.cg.healthcaresystem.ui;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
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
		  while(adminChoice!=8)
		  {
			  System.out.println("What function do you want to perform? \n. 1.Add new Center 2.Update an existing "
			  		+ "center 3.Remove an existing center \n 4.Add new Test in an existing center "
			  		+ "5.Update an existing Test 6.Remove a Test\n 7. Approve new appointments");
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
			  		break;
			  		
			  	case 3:
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
			  	
			  	case 4:
			  		
			  		
			  		break;
			  	case 5:
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
  	 case 2: 
  		 
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
