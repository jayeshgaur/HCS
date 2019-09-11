package com.cg.healthcaresystem.ui;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.service.UserService;
import com.cg.healthcaresystem.service.UserServiceImpl;



public class MyApp {

	public static void main(String[] args) 
	{
	UserService userService=new UserServiceImpl();
	int userRole=0,adminChoice=0,userChoice=0;
	while(userRole!=3)
	{
		userRole=adminChoice=userChoice=0;
	 System.out.println("Enter your role: \n 1.Admin 2.User 3.Exit");
	 Scanner sc = new Scanner(System.in);
	 userRole = sc.nextInt();
	 switch(userRole)
	 {
	  case 1:   // Admin functions
		  
		  while(adminChoice!=6)
		  {
			  adminChoice=0;
			  System.out.println("What function do you want to perform? \n. 1.Add new Center "
			  		+ "2.Remove an existing center \n 3.Add new Test in an existing center "
			  		+ "4.Remove a Test\n 5.Approve appointments\n 6.Exit");
			 
			  adminChoice = sc.nextInt();
			  switch(adminChoice)
			  {
			  	case 1: //Add Center
			  		System.out.println("Enter the name of the center:");
			  		sc.nextLine();
			  		String centerName = sc.nextLine();
			  		
			  		System.out.println("Enter the address of the center:");
			  		String centerAddress = sc.nextLine();
			  		
			  		System.out.println("Enter the contact number of the center:");
			  		String centerContactNo = sc.nextLine();
			  		try {
			  		UserServiceImpl.validateContactNo(centerContactNo);
			  		BigInteger centerContact=new BigInteger(centerContactNo);
			  		DiagnosticCenter center=new DiagnosticCenter(centerName, centerAddress, centerContact);
			  		if(userService.addCenter(center)!=null)
			  		{
			  			System.out.println("Center added successfully!");
			  		}
			  		else{
			  			System.out.println("Failed");
			  		}
			  		}catch(UserDefinedException e) {
			  			System.out.println(e.getMessage());
			  		}
			  		
			  		
			  		break;
			  	
			  		
			  	case 2: //Remove Center
			  		
			  		List<DiagnosticCenter> centerList1=userService.getCenterList();
			  		if(centerList1.size()<1)
			  		{
			  			System.out.println("No center in the system");
			  		}
			  		else
			  		{		
			  		System.out.println("Enter the id of center which you want to remove");
			  		for(int centerIndex=0;centerIndex<centerList1.size();centerIndex++)
			  		 {
			  	   		DiagnosticCenter d = centerList1.get(centerIndex);
			  	  		System.out.println(centerIndex+" CenterName: "+d.getCenterName()+" CenterID: "+d.getCenterId());
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
			  		}
			  		break;
			  	
			  	case 3: //Add Test
	//		  		System.out.println("Select the center in which you want to add test");
			  		List<DiagnosticCenter> centerList=userService.getCenterList();
			  		if(centerList.size()<1)
			  		{
			  			System.out.println("No centers in the system to add test into");
			  		}
			  		else {
			  		Iterator itr=centerList.iterator();
			  		int count=1;
			  		while(itr.hasNext())
			  		{
			  			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
			  			System.out.println(count+". CenterName: "+obj.getCenterName());
			  			count++;
			  			
			  		}
			  		
			  		System.out.println("Enter the centerName in which you want to add test");
			  		sc.nextLine();
			  		String centername=sc.nextLine();
			  		System.out.println("Enter the name of the test which you want to add in center "+centername);
			  		String testname=sc.nextLine();
			  		Test test=new Test(testname);
			  		if(userService.addTest(centername, test)!=null)
			  		{
			  			System.out.println("Test addedd successfully");
			  		}
			  		else
			  		{
			  			System.out.println("Not added");
			  		}
//			  		List<DiagnosticCenter> li2 = userService.getCenterList();
//			  		Iterator itr4=li2.iterator();
//			  		while(itr4.hasNext())
//			  		{
//			  			System.out.println(itr4.next());
//			  		}
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
			  			System.out.println(counter+". CenterName: "+obj.getCenterName()+" Center Id: "+obj.getCenterId());
			  			counter++;	
			  		}
			  		System.out.println("Enter the center Id in which you want to remove test");
			  		String removeCenterId="";
			  		try {
			  		removeCenterId=sc.next();
			  		UserServiceImpl.validateCenterId(removeCenterId, centerList1);
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
			  			System.out.println(counter+". ID: "+testList.get(i).getTestId()+"  Name: "+testList.get(i).getTestName());
			  			counter++;
			  		}
			  		System.out.println("Enter the id of the test which you want to remove");
			  		String removeTestId=sc.next();
			  		UserServiceImpl.validateTestid(removeTestId, removeCenterId, centerList1);
			  		if(userService.removeTest(removeCenterId,removeTestId))
			  		{
			  			System.out.println("Test deleted successfully");
			  		}
			  		else
			  		{
			  			System.out.println("Test is not present");
			  		}
			  		}
			  		catch(UserDefinedException e)
			  		{
			  			System.out.println(e.getMessage());
			  		}
			  		
			  		
			  		
			  		break;
			  	case 5: //ApproveAppointment
			  		
			  	  System.out.println("====List of diagnostic center=====");
			  	  List<DiagnosticCenter> diagnosticcenter=userService.getCenterList();
			  	  Iterator itr5=diagnosticcenter.iterator();
			  	  int count5=1;
			  	  String centerid5="";
			  	  while(itr5.hasNext())
			  	  {
			  	  DiagnosticCenter obj=(DiagnosticCenter) itr5.next();
			  	  System.out.println(count5+"."+obj.getCenterId()+" "+obj.getCenterName());
			  	  count5++;
			  	  }		  	 
			  	  System.out.println("Enter Diagnostic Center Id");
			  	 
			  	  try {
			  	  centerid5=sc.next();
			  	  UserServiceImpl.validateCenterId(centerid5, diagnosticcenter);
			  	System.out.println("=====List of appointments======");
			  	 
			  	  List<DiagnosticCenter> listofdc=userService.getCenterList();
			  	  for(int i=0;i<listofdc.size();i++)
			  	  {
			  	  if(listofdc.get(i).getCenterId().equals(centerid5))
			  	  {
			  	   List<Appointment> listofappointment=listofdc.get(i).getListOfAppointments();
			  	   if(listofappointment.size()<1)
			  	   {
			  		   System.out.println("There are no appointments!");
			  	   }
			  	   else
			  	   {
			  	   for(int j=0;j<listofappointment.size();j++)
			  	   {
			  	    System.out.println("Appointment ID: "+listofappointment.get(j).getAppointmentId()+" TestDetails: "+listofappointment.get(j).getTest()+" Status"+listofappointment.get(j).isApproved());
			  	   }
			  	  System.out.println("Enter the appointment id which you want to approve");
			  	  String appointmentid=sc.next();
			  	  UserServiceImpl.validateAppointmentId(appointmentid, listofappointment);
			  	  for(int k=0;k<listofappointment.size();k++)
			  	  {
			  	//	if(listofappointment.get(k).getAppointmentId().contentEquals(appointmentid))
			  		if(listofappointment.get(k).getAppointmentId().equals(appointmentid))
			  		{
			  		  if(listofappointment.get(k).setApproved(true))
			  		  {
			  			  System.out.println("Status updated");
			  		  }
			  		}
			  	  }
			  	   }
			  	  //
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
			  	 
			  	  }
			  	  catch(UserDefinedException e)
			  	  {
			  		  System.out.println(e.getMessage());
			  	  }
			  	 
			  		
			  		break;
			  	case 6: 
			  		break;
			  	default:
			  		System.out.println("Enter a proper function");
			  		break;
			  }
		  }
  		break;
  		
  	 case 2:  // User Functions
  		 
  		 while(userChoice!=3)
  		 {
  			 userChoice=0;
  			System.out.println("What function do u want to perform?\n 1.Registration\n 2.Make Appointments\n 3.Exit");
	  		 userChoice=sc.nextInt();
  			 switch(userChoice)
  			 {
  			 case 1: //User Registration
  				 System.out.println("-------------Registration-----------");
  				 System.out.println("Enter your name");
  				 sc.nextLine();
  				 String userName=sc.nextLine();
  				try
  				 {
  				 UserServiceImpl.validateName(userName);
  				
  				 System.out.println("Enter your password");
  				
 				 String userPassword=sc.nextLine();

 			
 				 UserServiceImpl.validatePassword(userPassword);
  				
  			
  				 System.out.println("Enter your contact number");
  				 String userContactNo = sc.next();
  				
  				 UserServiceImpl.validateContactNo(userContactNo);
  				
  				 BigInteger contactNo=new BigInteger(userContactNo);
  				 
  				 System.out.println("Enter your email");
  				 String userEmail = sc.next();
  				 
  					 UserServiceImpl.validateEmail(userEmail);
  				 
  				 System.out.println("Enter your age");
  				 Integer age = sc.nextInt();
  				 
  					 UserServiceImpl.validateAge(age);
  				
  				 System.out.println("Choose your gender    M.Male F.Female O.Other");
  				 String gender = sc.next();
  				
  					 UserServiceImpl.validateGender(gender);
  				
  				 
  				 User u;
  				 if(gender.equals("M"))
  				 {
  					 u = new User(userPassword, userName, contactNo, userEmail, 
  							 age, "Male"); 
  				 }
  				 else if(gender.equals("F"))
  				 {
  					 u = new User(userPassword, userName, contactNo, userEmail, 
  							 age, "Female"); 
  				 }
  				 else {
  					u = new User(userPassword, userName, contactNo, userEmail, 
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
  				 }
  				catch(UserDefinedException e)
  				{
  					System.out.println(e.getMessage());
  				}
   				 break;
   				 
  			 case 2: //Make Appointment
  				
		  		List<DiagnosticCenter> centerList1=userService.getCenterList();
		  		if(centerList1.size()<1)
		  		{
		  			System.out.println("Sorry, we have no active centers right now!");
		  		}
		  		else
		  		{
		  		System.out.println("Select the center where you want to book a test");
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
		  		try {
		  		String selectCenterIndex=sc.next();
		  		UserServiceImpl.validateCenterIndex(selectCenterIndex, centerList1);
		  		int selectCenterIndexNum = Integer.parseInt(selectCenterIndex);
		  		testList = centerList1.get(selectCenterIndexNum).getListOfTests();
		  		for(int testIndex=0;testIndex<testList.size();testIndex++)
		  		{
		  			Test t = testList.get(testIndex);
		  			System.out.println(testIndex+" Test Name"+t.getTestName());
		  		}
		  		System.out.println("Select from the above tests");
		  		String selectTestIndex = sc.next();
		  		UserServiceImpl.validateTestIndex(selectTestIndex, selectCenterIndexNum, testList);
		  		int selectTestIndexNum = Integer.parseInt(selectTestIndex);
		  		
		  		//datetime
		  		System.out.println("Enter date in the following format: DD/MM/yyyy HH:mm:ss");
		  		sc.nextLine();
		  		String dateString=sc.nextLine();
		  		UserServiceImpl.validateDate(dateString);
		  		SimpleDateFormat format=new SimpleDateFormat("d/M/yyyy H:m:s");
		  		Date date=format.parse(dateString);
		  		
		  		
		  		System.out.println("Enter your user ID");
		  		String userId = sc.next();
		  		userList = userService.getUserList();
		  		UserServiceImpl.validateUser(userId, userList);
		  		User user=null;;
		  		for(int userIndex=0;userIndex<userList.size();userIndex++)
		  		{
		  			if(userList.get(userIndex).getUserId().equals(userId))
		  			{
		  				user = userList.get(userIndex);
		  			}
		  		}
		  		Appointment ap = new Appointment(user,  testList.get(selectTestIndexNum), centerList1.get(selectCenterIndexNum), date);
		  		if(centerList1.get(selectCenterIndexNum).addAppointment(ap))
		  		{
		  			System.out.println("Appointment request submitted!");
		  		}
		  		userService.setCenterList(centerList1);
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
		  		}
		  		catch(UserDefinedException e)
		  		{
		  			System.out.println(e.getMessage());
		  		}catch(ParseException p)
		  		{
		  			System.out.println("Enter the datetime in the specified format!");
		  		}
		  		
		  		}
  				 break;
  			 case 3:
  				 break;
  			 default:
  				 System.out.println("Enter proper value!");
  				 break;
  			 }
  		 }
		break;
		
  	 case 3:
  		 break;
	 default:
		System.out.println("Enter a proper choice!");
	    break;	
	 }
	}

}

}
