package com.cg.healthcaresystem.ui;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
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
	  case 1:   // Admin functions
		  
		  while(adminChoice!=6)
		  {
			  System.out.println("What function do you want to perform? \n. 1.Add new Center "
			  		+ "2.Remove an existing center \n 3.Add new Test in an existing center "
			  		+ "4.Remove a Test\n 5.Approve appointments\n 6.Exit");
			  adminChoice = sc.nextInt();
			  switch(adminChoice)
			  {
			  	case 1: //Add Center
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
			  		else{
			  			System.out.println("Failed");
			  		}
			  		break;
			  	
			  		
			  	case 2: //Remove Center
			  		System.out.println("Enter the id of center which you want to remove");
			  		List<DiagnosticCenter> centerList1=userService.getCenterList();
			  		for(int centerIndex=0;centerIndex<centerList1.size();centerIndex++)
			  		{
			  			DiagnosticCenter d = centerList1.get(centerIndex);
			  			System.out.println(centerIndex+" CenterName: "+d.getCenterName()+" "+d.getCenterId());
			  		}
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
			  	
			  	case 3: //Add Test
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
			  	case 4: // Remove Test
			  		System.out.println("Select the center where you want to delete test");
			  		centerList1=userService.getCenterList();
			  		List<Test> testList = new ArrayList<Test>();
			  		Iterator itr1=centerList1.iterator();
			  		int counter=1;
			  		while(itr1.hasNext())
			  		{
			  			DiagnosticCenter obj=(DiagnosticCenter) itr1.next();
			  			System.out.println(counter+"."+obj.getCenterName()+"Center Id"+obj.getCenterId());
			  			counter++;	
			  		}
			  		System.out.println("Enter the center Id in which you want to remove test");
			  		String removeCenterId=sc.next();
			  		for(int i=0; i<centerList1.size();i++)
			  		{
			  			if(centerList1.get(i).getCenterId().equals(removeCenterId))
			  			{
			  				testList = centerList1.get(i).getListOfTests();
			  			}
			  		}
			  		counter=1;
			  		for(int i=0;i<testList.size();i++)
			  		{
			  			System.out.println(counter+". ID: "+testList.get(i).getTestId()+"Test Name"+testList.get(i).getTestName());
			  			counter++;
			  		}
			  			System.out.println("Enter the id of the test which you want to remove");
			  		String removeTestId=sc.next();
			  		if(userService.removeTest(removeCenterId,removeTestId))
			  		{
			  			System.out.println("Test deleted successfully");
			  		}
			  		else
			  		{
			  			System.out.println("Test is not present");
			  		}
			  		
			  		
			  		break;
			  	case 5: //ApproveAppointment
			  		
			  	  System.out.println("====List of diagnostic center=====");
			  	  List<DiagnosticCenter> diagnosticcenter=userService.getCenterList();
			  	  Iterator itr5=diagnosticcenter.iterator();
			  	  int count5=1;
			  	  while(itr5.hasNext())
			  	  {
			  	  DiagnosticCenter obj=(DiagnosticCenter) itr5.next();
			  	  System.out.println(count5+"."+obj.getCenterId()+" "+obj.getCenterName());
			  	  count5++;
			  	  }
			  	 
			  	  System.out.println("Enter Diagnostic Center Id");
			  	  String centerid5=sc.next();
			  	 
			  	  System.out.println("=====List of appointments======");
			  	 
			  	  List<DiagnosticCenter> listofdc=userService.getCenterList();
			  	  for(int i=0;i<listofdc.size();i++)
			  	  {
			  	  System.out.println(listofdc.get(0));
			  	  if(listofdc.get(i).getCenterId().equals(centerid5))
			  	  {
			  	  List<Appointment> listofappointment=listofdc.get(i).getListOfAppointments();
			  	  for(int j=0;j<listofappointment.size();j++)
			  	  {
			  	  System.out.println(listofappointment.get(j));
			  	  }
			  	  System.out.println("Enter the appointment id which you want to approve");
			  	  String appointmentid=sc.next();
			  	  for(int k=0;k<listofappointment.size();k++)
			  	  {
			  		if(listofappointment.get(k).getAppointmentId().contentEquals(appointmentid))
			  		{
			  		  listofappointment.get(k).setApproved(true);
			  		 }
			  	  }
			  	  }
			  	  else
			  	  {
			  	  System.out.println("Diagnostic center with "+centerid5+" not found");
			  	 
			  	  }
			  	  }
			  	  //Iterator itr6=listofappointment.iterator();
			  	  //while(itr6.hasNext())
			  	  //{
			  	  //Appointment obj=(Appointment)itr6.next();
			  	  //System.out.println("User name "+obj.getUser().getUserId()+" "+obj.getUser().getUserName()+" "+obj.getUser().getAge()+" "+obj.getUser().getGender()+" "+listofAppointment.get);
			  	  //}
			  		
			  		break;
			  	case 6: 
			  		break;
			  	default:
			  		System.out.println("Enter a proper function");
		
			  }
		  }
  		break;
  		
  	 case 2:  // User Functions
  		 
  		 while(userChoice!=3)
  		 {
  			System.out.println("What function do u want to perform?\n 1.Registration\n 2.Make Appointments\n 3.Exit");
	  		 userChoice=sc.nextInt();
  			 switch(userChoice)
  			 {
  			 case 1: //User Registration
  				 System.out.println("-------------Registration-----------");
  				 System.out.println("Enter your password");
 				 String userPassword=sc.next();
  				 System.out.println("Enter your name");
  				 String userName=sc.next();
  				 System.out.println("Enter your contact number");
  				 BigInteger userContactNo = sc.nextBigInteger();
  				 System.out.println("Enter your email");
  				 String userEmail = sc.next();
  				 System.out.println("Enter your age");
  				 Integer age = sc.nextInt();
  				 System.out.println("Choose your gender    M.Male F.Female O.Other");
  				 String gender = sc.next();
  				 User u;
  				 if(gender.equals("M"))
  				 {
  					 u = new User(userPassword, userName, userContactNo, userEmail, 
  							 age, "Male"); 
  				 }
  				 else if(gender.equals("F"))
  				 {
  					 u = new User(userPassword, userName, userContactNo, userEmail, 
  							 age, "Female"); 
  				 }
  				 else {
  					u = new User(userPassword, userName, userContactNo, userEmail, 
 							 age, "other"); 
  				 }
  				 System.out.println("Your userID is: "+userService.register(u));
  				 
//  				 try
//  				 {
//  					 UserServiceImpl.validatePassword(password);
//  				 }
//  				 catch(UserDefinedException e)
//  				 {
//  					 System.out.println(e.getMessage());
//  					 break;
//  				 }
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
   				 break;
   				 
  			 case 2: //Make Appointment
  				System.out.println("Select the center where you want to book a test");
		  		List<DiagnosticCenter> centerList1=userService.getCenterList();
		  		List<Test> testList = new ArrayList<Test>();
		  		List<User> userList = new ArrayList<User>();
		  		for(int centerIndex=0;centerIndex<centerList1.size();centerIndex++)
		  		{
		  			DiagnosticCenter d = centerList1.get(centerIndex);
		  			System.out.println(centerIndex+" CenterName: "+d.getCenterName());
		  		}
//		  		Iterator itr1=centerList1.iterator();
//		  		int counter=1;
//		  		while(itr1.hasNext())
//		  		{
//		  			DiagnosticCenter obj=(DiagnosticCenter) itr1.next();
//		  			System.out.println(counter+"."+obj.getCenterName()+"Center Id"+obj.getCenterId());
//		  			counter++;	
//		  		}
		  		int selectCenterIndex=sc.nextInt();
		  		testList = centerList1.get(selectCenterIndex).getListOfTests();
		  		for(int testIndex=0;testIndex<testList.size();testIndex++)
		  		{
		  			Test t = testList.get(testIndex);
		  			System.out.println(testIndex+" Test Name"+t.getTestName());
		  		}
		  		int selectTestIndex = sc.nextInt();
		  		System.out.println("Enter your user ID");
		  		String userId = sc.next();
		  		userList = userService.getUserList();
		  		User user=new User();
		  		for(int userIndex=0;userIndex<userList.size();userIndex++)
		  		{
		  			if(userList.get(userIndex).getUserId().equals(userId))
		  			{
		  				user = userList.get(userIndex);
		  			}
		  		}
		  		Appointment ap = new Appointment(user,  testList.get(selectTestIndex), centerList1.get(selectCenterIndex));
		  		System.out.println(ap.toString());
//		  		for(int i=0;i<centerList1.size();i++)
//		  		{
//		  			if(centerList1.get(select).getCenterId().equals(selectCenterId));
//		  			{
//		  				testList = centerList1.get(i).getListOfTests();
//		  			}
//		  			itr1 = testList.iterator();
//		  			while(itr1.hasNext())
//		  			{
//		  				Test t = (Test) itr1.next();
//		  				System.out.println("Test Name: "+t.getTestName()+" Test ID: "+t.getTestId());
//		  			}
//		  			System.out.println("Select the id of the test you want to book an appointment for");
//		  			String selectTestId = sc.next();
//		  			System.out.println("Enter your user ID: ");
//		  			String userId = sc.next();
//		  			
//		  			Appointment ap = new Appointment("")
//		  		}
  				 break;
  			 default:
  				 System.out.println("Enter proper value!");
  				 break;
  			 }
  		 }
		break;
	 default:
		System.out.println("Enter a proper choice!");
	    break;	
	 }
	}

}

}
