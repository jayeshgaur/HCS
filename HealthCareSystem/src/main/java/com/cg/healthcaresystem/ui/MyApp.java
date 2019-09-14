package com.cg.healthcaresystem.ui;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
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
		String choices = "";
		DiagnosticCenter diagnosticCenter = null;
		Test test = null;
		Appointment appointment = null;
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
								while (testListIterator.hasNext()) {
									test = testListIterator.next();
									System.out.println(
											"Test ID: " + test.getTestId() + " Test Name: " + test.getTestName());
								}

								// Get Test Id of the test to be removed
								System.out.println("Enter the id of the test which you want to remove");
								String removeTestId = scanner.next();

								// Remove test from the list if test id is correct
								if (userService.removeTest(removeCenterId,
										userService.validateTestId(removeTestId, removeCenterId, centerList),
										centerList)) {
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
						List<Appointment> appointmentList = null;
						appointment = null;
						System.out.println("====List of diagnostic center=====");
						centerList = userService.getCenterList();

						// Check if there's a center present
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

								// get list of appointments in the center
								System.out.println("=====List of appointments======");
								diagnosticCenterIterator = centerList.iterator();
								while (diagnosticCenterIterator.hasNext()) {
									diagnosticCenter = diagnosticCenterIterator.next();
									if (diagnosticCenter.getCenterId().equals(centerId)) {
										appointmentList = diagnosticCenter.getListOfAppointments();
										break;
									}
								}

								// Check if there are any appointments in it
								if (appointmentList.size() < 1) {
									System.out.println("There are no appointments yet in this center");
								} else {
									Iterator<Appointment> appointmentListIterator = appointmentList.iterator();
									// print list of appointments
									while (appointmentListIterator.hasNext()) {
										appointment = appointmentListIterator.next();
										System.out.println("Appointment ID: " + appointment.getAppointmentId()
												+ " Center Name: " + appointment.getCenter().getCenterName()
												+ " Test Name: " + appointment.getTest().getTestName()
												+ " Date and Time: " + appointment.getDate() + " Status: "
												+ appointment.isApproved());
									}
									System.out.println("Enter the appointment ID to approve");
									String appointmentId = userService.validateAppointmentId(scanner.next(),
											appointmentList);
									if (userService.approveAppointment(appointmentId, appointmentList)) {
										System.out.println("Status updated successfully!");
									} else {
										System.out.println("Status update failed, try again.");
									}
								}
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
							"What function do u want to perform?\n 1.Registration\n 2.Make Appointments\n 3.View Appointments \n4.Exit");

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

						try {
							String userName = userService.validateName(scanner.nextLine());

							System.out.println("Enter your password");
							String userPassword = userService.validatePassword(scanner.nextLine());

							System.out.println("Enter your contact number");
							String userContactNo = userService.validateContactNo(scanner.next());
							BigInteger contactNo = new BigInteger(userContactNo);

							System.out.println("Enter your email");
							String userEmail = userService.validateEmail(scanner.next());

							System.out.println("Enter your age");
							Integer age = userService.validateAge(scanner.nextInt());

							System.out.println("Choose your gender    M.Male F.Female O.Other");
							String gender = userService.validateGender(scanner.next());

							User user;
							if (gender.equals("M")) {
								user = new User(userPassword, userName, contactNo, userEmail, age, "Male");
							} else if (gender.equals("F")) {
								user = new User(userPassword, userName, contactNo, userEmail, age, "Female");
							} else {
								user = new User(userPassword, userName, contactNo, userEmail, age, "other");
							}
							/* add user to userList */
							System.out.println("Your userID is: " + userService.register(user));
						} catch (UserDefinedException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 2: // Make Appointment

						// Get Center list
						centerList = userService.getCenterList();

						// Check if centers exist or not
						if (centerList.size() < 1) {
							System.out.println("Sorry, we have no active centers right now!");
						} else {

							System.out.println("Select the center where you want to book a test");

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
								// Get Center Id to make an appointment in
								centerId = userService.validateCenterId(scanner.next(), centerList);

								// Get List of tests
								diagnosticCenterIterator = centerList.iterator();
								while (diagnosticCenterIterator.hasNext()) {
									diagnosticCenter = diagnosticCenterIterator.next();
									if (diagnosticCenter.getCenterId().equals(centerId)) {
										testList = diagnosticCenter.getListOfTests();
										// break the loop to preserve the selected center object
										break;
									}
								}
								System.out.println("Enter the test id you want to book an appointment for: ");

								// Print all tests present in the selected center
								Iterator<Test> testListIterator = testList.iterator();
								while (testListIterator.hasNext()) {
									test = testListIterator.next();
									System.out.println(
											"TestName: " + test.getTestName() + " TestID: " + test.getTestId());
								}

								String testId = userService.validateTestId(scanner.next(), centerId, centerList);

								// Get the test object corresponding to the testId
								testListIterator = testList.iterator();
								while (testListIterator.hasNext()) {
									test = testListIterator.next();
									if (test.getTestId().equals(testId)) {
										break;
									}
								}

								System.out.println("Enter your user id: ");
								// Get user Id
								User user = userService.validateUserId(scanner.next());

								// Date
								System.out.println("Enter date in the format: M/d/yyyy");
								LocalDate date = userService.validateDate(scanner.next());

								// Time
								System.out.println("Enter appointment time in the format: HH:mm");
								LocalTime time = userService.validateTime(scanner.next());

								appointment = new Appointment(user, test, diagnosticCenter, date, time);
								if (null != userService.addAppointment(appointment, centerId, centerList)) {
									System.out.println("Added successfully");
								}

							} catch (UserDefinedException e) {
								System.out.println(e.getMessage());
							}

						}
						break;
					case 3:
						//Get Center List
						centerList = userService.getCenterList();
						
						//Check if centers exist
						if (centerList.size() < 1)
							System.out.println("We are not functional yet!");
						else {
							try {
								
								//Enter user id
								System.out.println("Enter your user ID: ");
								scanner.nextLine();
								User user = userService.validateUserId(scanner.nextLine());
								List<Appointment> userAppointmentList = userService.getAppointmentList(user);
								if(null != userAppointmentList) {
									Iterator<Appointment> appointmentIterator = userAppointmentList.iterator();
									while(appointmentIterator.hasNext()) {
										appointment = appointmentIterator.next();
										System.out.println("AppointmentID: "+appointment.getAppointmentId()+" TestName: "+appointment.getTest().getTestName()+"\nCenterLocation: "+appointment.getCenter().getCenterAddress()+" Status: "+appointment.isApproved());
									}
								}
								else {
									System.out.println("You have no appointments with us");
								}
							} catch (Exception e) {
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
