package com.cg.healthcaresystem.exception;

public class UserErrorMessage {
	public static final String userErrorPassword = "Enter valid password.\\n Password should must contain one number,one capital letter,one small letter and one special character";
	public static final String userErrorUserName = "Enter a valid name.\n Name should start with a capital letter";
	public static final String userErrorStringContactNo = "Enter valid contact number";
	public static final String userErrorContactNoLength = "Contact number should have 10 digits";
	public static final String userErrorEmailId = "Enter valid email id.";
	public static final String userErrorUserAge = "Enter valid age";
	public static final String userErrorUserGender = "Enter valid gender";
	public static final String userErrorInvalidCenterId = "Enter a proper center id";
	public static final String userErrorInvalidTestId = "Enter correct test id";
	public static final String userErrorInvalidAppointmentId = "Enter correct appointment id!";
	public static final String userErrorAddTestFailed = "Failed to add test";
	public static final String userErrorInvalidUserId = "Invalid User Id.";
	public static final String userErrorInvalidDateFormat = "Please enter the date in the specified format";
	public static final String userErrorPastDate = "You cannot book an appointment today or in the past!";
	public static final String userErrorInvalidTimeFormat = "Please enter the time in the specified format";
	public static final String userErrorNonWorkingHours = "Our working hours are only from 10am to 8pm.";
	public static final String userErrorNoCenterAdded="Add center failed";
	public static final String userErrorNoCenterDeleted="Delete center failed";
	public static final String userErrorNoTestAdded="Add test failed";
	public static final String userErrorNoTestDeleted="Delete test failed";
}
