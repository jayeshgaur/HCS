package com.cg.healthcaresystem.ui;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.service.UserService;
import com.cg.healthcaresystem.service.UserServiceImpl;

public class MyApp {

	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		int userRole = 0, adminChoice = 0, userChoice = 0;
		String centerName = "";
		String centerId = "";
		String userId = "";
		String choices = "";
		DiagnosticCenter diagnosticCenter;
		Test test;
		List<DiagnosticCenter> centerList = null;
		List<Test> testList = null;
		while (userRole != 3) {
			userRole = adminChoice = userChoice = 0;
			System.out.println("Enter your role: \n 1.Admin 2.User 3.Exit");
			Scanner scanner = new Scanner(System.in);

			// validate numeric choice for menu
			while (true) {
				choices = scanner.next();
				try {
					userRole = Integer.parseInt(choices);
					break;
				} catch (Exception parseException) {
					System.out.println("Enter a numeric choice!");
				}
			}

			switch (userRole) {
			case 1: // Admin functions

				while (adminChoice != 6) {
					adminChoice = 0;
					System.out.println("What function do you want to perform? \n1.Add new Center "
							+ "\n2.Remove an existing center \n3.Add new Test in an existing center "
							+ "\n4.Remove a Test\n5.Approve appointments\n6.Exit");

					// validate adminChoice
					while (true) {
						choices = scanner.next();
						try {
							adminChoice = Integer.parseInt(choices);
							break;
						} catch (Exception parseException) {
							System.out.println("Enter a numeric choice!");
						}
					}

					switch (adminChoice) {
					case 1: // Add Center

						// Get new center details
						System.out.println("Enter the name of the center:");
						scanner.nextLine();
						centerName = scanner.nextLine();
						System.out.println("Enter the address of the center:");
						String centerAddress = scanner.nextLine();
						System.out.println("Enter the contact number of the center:");
						String centerContactNo = scanner.nextLine();
						try {
							// validate contact number and add
							BigInteger centerContact = new BigInteger(userService.validateContactNo(centerContactNo));

							// create center object to add to centerlist
							diagnosticCenter = new DiagnosticCenter(centerName, centerAddress, centerContact);

							// add object to list
							if (null != userService.addCenter(diagnosticCenter)) {
								System.out.println("Center added successfully!");
							} else {
								System.out.println("Could not add center. Try again!");
							}
						} catch (UserDefinedException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 2: // Remove Center

						// Get list of centers to check if center(s) exists or not!
						centerList = userService.getCenterList();
						if (centerList.size() < 1) {
							System.out.println("No center in the system");
						} else {

							// print all existing centers to choose from
							Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
							while (diagnosticCenterIterator.hasNext()) {
								diagnosticCenter = diagnosticCenterIterator.next();
								System.out.println("CenterName: " + diagnosticCenter.getCenterName() + " CenterId: "
										+ diagnosticCenter.getCenterId() + " Address: "
										+ diagnosticCenter.getCenterAddress());
							}

							// Get CenterId from admin to delete center
							System.out.println("Enter the id of center which you want to remove");
							centerId = scanner.next();

							// validate ID and remove
							try {
								if (userService.removeCenter(userService.validateCenterId(centerId, centerList))) {
									System.out.println("Center deleted successfully");
								} else {
									System.out.println("No diagnostic center found with centerid " + centerId);
								}
							} catch (UserDefinedException userDefinedException) {
								System.out.println(userDefinedException.getMessage());
							}
						}
						break;

					case 3: // Add Test
						// Get list of centers
						centerList = userService.getCenterList();

						// Check if there's a center present in the system to perform "Add Test"
						if (centerList.size() < 1) {
							System.out.println("No centers in the system to add test into");
						} else {

							// Get Test Details First
							System.out.println("Enter the name of the test which you want to add in center ");
							scanner.nextLine();
							String testName = scanner.nextLine();

							// print all existing centers to choose from
							Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
							while (diagnosticCenterIterator.hasNext()) {
								diagnosticCenter = diagnosticCenterIterator.next();
								System.out.println("CenterName: " + diagnosticCenter.getCenterName() + " CenterId: "
										+ diagnosticCenter.getCenterId() + " Address: "
										+ diagnosticCenter.getCenterAddress());
							}

							// Select Center Id
							System.out.println("Enter the Center Id of the Center in which you want to add test");

							centerId = scanner.nextLine();

							// Create Test object to add
							test = new Test(testName);

							// validate center id and add to center
							try {
								if (null != userService.addTest(userService.validateCenterId(centerId, centerList),
										test)) {
									System.out.println("Test addedd successfully");
								} else {
									System.out.println("Not added. Please try again");
								}
							} catch (UserDefinedException e) {
								System.out.println(e.getMessage());
							}
						}
						break;

					case 4: // Remove Test

						// Get list of existing centers
						centerList = userService.getCenterList();

						// Check if list has centers
						if (centerList.size() < 1) {
							System.out.println("There is no center in the system!");
						} else {
							System.out.println("Select the center where you want to delete test");

							// print all existing centers to choose from
							Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
							while (diagnosticCenterIterator.hasNext()) {
								diagnosticCenter = diagnosticCenterIterator.next();
								System.out.println("CenterName: " + diagnosticCenter.getCenterName() + " CenterId: "
										+ diagnosticCenter.getCenterId() + " Address: "
										+ diagnosticCenter.getCenterAddress());
							}

							// Take center id to get list of test to be removed
							String removeCenterId = "";
							try {
								removeCenterId = scanner.next();

								// validate center Id
								removeCenterId = userService.validateCenterId(removeCenterId, centerList);

								// Retrieve list of tests from the selected center id
								Iterator<DiagnosticCenter> iterator = centerList.iterator();
								while (iterator.hasNext()) {
									diagnosticCenter = iterator.next();
									if (diagnosticCenter.getCenterId().equals(removeCenterId)) {
										testList = diagnosticCenter.getListOfTests();
									}
								}

								// Display test list from the selected center id
								Iterator<Test> testListIterator = testList.iterator();
								while(testListIterator.hasNext()) {
									test = testListIterator.next();
									System.out.println("Test ID: "+test.getTestId()+" Test Name: "+test.getTestName());
								}

								// Get Test Id of the test to be removed
								System.out.println("Enter the id of the test which you want to remove");
								String removeTestId = scanner.next();

								// Remove test from the list if test id is correct
								if (userService.removeTest(removeCenterId,userService.validateTestid(removeTestId, removeCenterId, centerList),centerList)) {
									System.out.println("Test deleted successfully");
								} else {
									System.out.println("Test is not present");
								}
							} catch (UserDefinedException e) {
								System.out.println(e.getMessage());
							}
						}

						break;
					case 5: // ApproveAppointment

						// Print list of diagnostic centers
						System.out.println("====List of diagnostic center=====");
						centerList = userService.getCenterList();
						
						//Check if there's a center present
						if (centerList.size() < 1) {
							System.out.println("Create a new center first");
						} else {
							Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
							while (diagnosticCenterIterator.hasNext()) {
								diagnosticCenter = diagnosticCenterIterator.next();
								System.out.println("CenterName: " + diagnosticCenter.getCenterName() + " CenterId: "
										+ diagnosticCenter.getCenterId() + " Address: "
										+ diagnosticCenter.getCenterAddress());
							}	

							System.out.println("Enter Diagnostic Center Id");
							try {

								// Accept center id
								centerId = userService.validateCenterId(scanner.next(), centerList);

								//get list of appointments in the center
								System.out.println("=====List of appointments======");
								for (int i = 0; i < centerList.size(); i++) {
									if (centerList.get(i).getCenterId()
											.equals(centerId)) {
										List<Appointment> listOfAppointments = centerList.get(i)
												.getListOfAppointments();
										if (listOfAppointments.size() < 1) {
											System.out.println("There are no appointments!");
										} else {
											for (int j = 0; j < listOfAppointments.size(); j++) {
												System.out.println("Appointment ID: "
														+ listOfAppointments.get(j).getAppointmentId() + " UserId: "
														+ listOfAppointments.get(j).getUser().getUserId()
														+ " TestDetails: "
														+ listOfAppointments.get(j).getTest().getTestName()
														+ " Status: " + listOfAppointments.get(j).isApproved());
											}
											System.out.println("Enter the appointment id which you want to approve");
											String appointmentId = scanner.next();
											UserServiceImpl.validateAppointmentId(appointmentId, listOfAppointments);
											for (int k = 0; k < listOfAppointments.size(); k++) {
												// if(listofappointment.get(k).getAppointmentId().contentEquals(appointmentid))
												if (listOfAppointments.get(k).getAppointmentId()
														.equals(appointmentId)) {
													if (listOfAppointments.get(k).setApproved(true)) {
														System.out.println("Status updated");
														break;
													}
												}
											}
											break;
										}

									}

								}
								// Iterator itr6=listofappointment.iterator();
								// while(itr6.hasNext())
								// {
								// Appointment obj=(Appointment)itr6.next();
								// System.out.println("User name "+obj.getUser().getUserId()+"
								// "+obj.getUser().getUserName()+" "+obj.getUser().getAge()+"
								// "+obj.getUser().getGender()+" "+listofAppointment.get);
								// }

							} catch (UserDefinedException e) {
								System.out.println(e.getMessage());
							}
						}

						break;
					case 6:
						break;
					default:
						System.out.println("Enter a proper choice");
						break;
					}
				}
				break;

			case 2: // User Functions

				while (userChoice != 4) {
					userChoice = 0;
					System.out.println(
							"What function do u want to perform?\n 1.Registration\n 2.Make Appointments\n 3.View Appointments 4.Exit");

					// validate user choice
					while (true) {
						choices = scanner.next();
						try {
							userChoice = Integer.parseInt(choices);
							break;
						} catch (Exception parseException) {
							System.out.println("Enter a numeric choice!");
						}
					}

					switch (userChoice) {
					case 1: // User Registration
						System.out.println("-------------Registration-----------");
						System.out.println("Enter your name");
						scanner.nextLine();
						String userName = scanner.nextLine();
						try {
							UserServiceImpl.validateName(userName);

							System.out.println("Enter your password");

							String userPassword = scanner.nextLine();

							UserServiceImpl.validatePassword(userPassword);

							System.out.println("Enter your contact number");
							String userContactNo = scanner.next();

							BigInteger contactNo = new BigInteger(userService.validateContactNo(userContactNo));

							System.out.println("Enter your email");
							String userEmail = scanner.next();

							UserServiceImpl.validateEmail(userEmail);

							System.out.println("Enter your age");
							Integer age = scanner.nextInt();

							UserServiceImpl.validateAge(age);

							System.out.println("Choose your gender    M.Male F.Female O.Other");
							String gender = scanner.next();

							UserServiceImpl.validateGender(gender);

							User u;
							if (gender.equals("M")) {
								u = new User(userPassword, userName, contactNo, userEmail, age, "Male");
							} else if (gender.equals("F")) {
								u = new User(userPassword, userName, contactNo, userEmail, age, "Female");
							} else {
								u = new User(userPassword, userName, contactNo, userEmail, age, "other");
							}
							/* add user to userList */
							System.out.println("Your userID is: " + userService.register(u));

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
						} catch (UserDefinedException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 2: // Make Appointment

						centerList = userService.getCenterList();
						if (centerList.size() < 1) {
							System.out.println("Sorry, we have no active centers right now!");
						} else {
							System.out.println("Select the center where you want to book a test");
							List<User> userList = null;

//		  		Iterator itr1=centerList1.iterator();
//		  		int counter=1;
//		  		while(itr1.hasNext())
//		  		{
//		  			DiagnosticCenter obj=(DiagnosticCenter) itr1.next();
//		  			System.out.println(counter+"."+obj.getCenterName()+"Center Id"+obj.getCenterId());
//		  			counter++;	
//		  		}

							// print all existing centers to choose from
							Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
							while (diagnosticCenterIterator.hasNext()) {
								diagnosticCenter = diagnosticCenterIterator.next();
								System.out.println("CenterName: " + diagnosticCenter.getCenterName() + " CenterId: "
										+ diagnosticCenter.getCenterId() + " Address: "
										+ diagnosticCenter.getCenterAddress());
							}

							try {
								String selectCenterIndex = scanner.next();
								UserServiceImpl.validateCenterIndex(selectCenterIndex, centerList);
								int selectCenterIndexNum = Integer.parseInt(selectCenterIndex);
								testList = centerList.get(selectCenterIndexNum).getListOfTests();
								for (int testIndex = 0; testIndex < testList.size(); testIndex++) {
									Test t = testList.get(testIndex);
									System.out.println(testIndex + " Test Name: " + t.getTestName());
								}
								System.out.println("Select from the above tests");
								String selectTestIndex = scanner.next();
								UserServiceImpl.validateTestIndex(selectTestIndex, selectCenterIndexNum, testList);
								int selectTestIndexNum = Integer.parseInt(selectTestIndex);

								// datetime
								System.out.println("Enter date in the following format: DD/MM/yyyy HH:mm:ss");
								scanner.nextLine();
								String dateString = scanner.nextLine();
								UserServiceImpl.validateDate(dateString);
								SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy H:m:s");
								Date date = format.parse(dateString);
								System.out.println("Enter your user ID");
								userId = scanner.next();
								userList = userService.getUserList();
								UserServiceImpl.validateUserId(userId, userList);
								User user = null;
								;
								for (int userIndex = 0; userIndex < userList.size(); userIndex++) {
									if (userList.get(userIndex).getUserId().equals(userId)) {
										user = userList.get(userIndex);
									}
								}
								Appointment ap = new Appointment(user, testList.get(selectTestIndexNum),
										centerList.get(selectCenterIndexNum), date);
								if (centerList.get(selectCenterIndexNum).addAppointment(ap)) {
									System.out.println("Appointment request submitted!");
								}
								userService.setCenterList(centerList);
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
							} catch (UserDefinedException e) {
								System.out.println(e.getMessage());
							} catch (ParseException p) {
								System.out.println("Enter the datetime in the specified format!");
							}

						}
						break;
					case 3:
						centerList = userService.getCenterList();
						List<User> userList = userService.getUserList();
						if (centerList.size() < 1)
							System.out.println("We are not functional yet!");
						else {
							try {
								System.out.println("Enter your user ID: ");
								scanner.nextLine();
								userId = scanner.nextLine();
								int appointmentCount = 0;
								UserServiceImpl.validateUserId(userId, userList);
								for (int centerIndex = 0; centerIndex < centerList.size(); centerIndex++) {
									for (int appointmentListIndex = 0; appointmentListIndex < centerList
											.get(centerIndex).getListOfAppointments().size(); appointmentListIndex++) {
										if (centerList.get(centerIndex).getListOfAppointments()
												.get(appointmentListIndex).getUser().getUserId().equals(userId)) {
											System.out.println("AppointmentID: "
													+ centerList.get(centerIndex).getListOfAppointments()
															.get(appointmentListIndex).getAppointmentId()
													+ " TestName: "
													+ centerList.get(centerIndex).getListOfAppointments()
															.get(appointmentListIndex).getTest().getTestId()
													+ " CenterId: "
													+ centerList.get(centerIndex).getListOfAppointments()
															.get(appointmentListIndex).getCenter().getCenterName()
													+ " App Date: "
													+ centerList.get(centerIndex).getListOfAppointments()
															.get(appointmentListIndex).getDate());
											appointmentCount++;
										}
									}
								}
								if (appointmentCount == 0) {
									System.out.println("You don't have any appointments!");
								}
							} catch (UserDefinedException e) {
								System.out.println(e.getMessage());
							}
						}
						break;
					case 4:
						break;
					default:
						System.out.println("Enter proper value!");
						break;
					}
				}
				break;

			case 3:
				scanner.close();
				break;
			default:
				System.out.println("Enter a proper choice!");
				break;
			}
		}

	}

}
