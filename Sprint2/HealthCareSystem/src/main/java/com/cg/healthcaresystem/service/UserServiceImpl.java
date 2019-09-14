package com.cg.healthcaresystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cg.healthcaresystem.dao.UserDao;
import com.cg.healthcaresystem.dao.UserDaoImpl;
import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.exception.UserErrorMessage;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		return userDao.addCenter(center);
	}

	public boolean removeCenter(String centerId) {
		return userDao.removeCenter(centerId);
	}

	public Test addTest(String centerId, Test test) throws UserDefinedException {
		List<DiagnosticCenter> centerList = userDao.getCenterList();
		DiagnosticCenter diagnosticCenter = null;
		Iterator<DiagnosticCenter> centerListIterator = centerList.iterator();
		while (centerListIterator.hasNext()) {
			diagnosticCenter = centerListIterator.next();
			if (diagnosticCenter.getCenterId().equals(centerId)) {
				diagnosticCenter.getListOfTests().add(test);
				return test;
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorAddTestFailed);
	}

	public boolean removeTest(String removeCenterId, String removeTestId, List<DiagnosticCenter> centerList) {
		List<Test> testList = null;
		DiagnosticCenter diagnosticCenter = null;
		Test test = null;
		Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
		while (diagnosticCenterIterator.hasNext()) {
			diagnosticCenter = diagnosticCenterIterator.next();
			if (diagnosticCenter.getCenterId().equals(removeCenterId)) {
				testList = diagnosticCenter.getListOfTests();
				break;
			}
		}

		Iterator<Test> testListIterator = testList.iterator();
		while (testListIterator.hasNext()) {
			test = testListIterator.next();
			if (test.getTestId().equals(removeTestId)) {
				break;
			}

		}
		return testList.remove(test);
	}

	public String register(User user) {
		return userDao.register(user);
	}

	public List<DiagnosticCenter> getCenterList() {
		return userDao.getCenterList();
	}

	public boolean setCenterList(List<DiagnosticCenter> centerList) {
		return userDao.setCenterList(centerList);
	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}

	public boolean setUserList(List<User> li) {
		return userDao.setUserList(li);
	}

	public boolean approveAppointment(String appointmentId, List<Appointment> appointmentList) {
		boolean status = false;
		Iterator<Appointment> appointmentListIterator = appointmentList.iterator();
		Appointment appointment;
		while (appointmentListIterator.hasNext()) {
			appointment = appointmentListIterator.next();
			if (appointment.getAppointmentId().equals(appointmentId)) {
				status = appointment.setApproved(true);
			}
		}
		return status;
	}

	public String validatePassword(String userPassword) throws UserDefinedException {
		if(userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
			return userPassword;			
		}
		throw new UserDefinedException(UserErrorMessage.userErrorPassword);
	}

	public String validateName(String userName) throws UserDefinedException {

		if (userName.matches("^[A-Z].*")) {
			return userName;
		}
		throw new UserDefinedException(UserErrorMessage.userErrorUserName);
	}

	public String validateContactNo(String userContactNo) throws UserDefinedException {
		if(userContactNo.matches("^[0-9]+")) {
			if(userContactNo.length() != 10) {
				throw new UserDefinedException(UserErrorMessage.userErrorContactNoLength);
			}else {
				return userContactNo;
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorStringContactNo);
	}

	public String validateEmail(String userEmail) throws UserDefinedException {
		if(userEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			return userEmail;
		}
		throw new UserDefinedException(UserErrorMessage.userErrorEmailId);
	}

	public Integer validateAge(Integer age) throws UserDefinedException {
		if (age <= 0 && age > 110) {
			throw new UserDefinedException(UserErrorMessage.userErrorUserAge);
		}
		return age;
	}

	public String validateGender(String gender) throws UserDefinedException {
		if (!(gender.equals("M") || gender.equals("F") || gender.equals("O"))) {
			throw new UserDefinedException(UserErrorMessage.userErrorUserGender);
		}
		return gender;

	}

	public String validateCenterId(String centerId, List<DiagnosticCenter> centerList) throws UserDefinedException {
		for (Iterator<DiagnosticCenter> iterator = centerList.iterator(); iterator.hasNext();) {
			DiagnosticCenter diagnosticCenter = iterator.next();
			if (diagnosticCenter.getCenterId().equals(centerId))
				return centerId;

		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidCenterId);
	}

	public String validateTestId(String removeTestId, String centerId, List<DiagnosticCenter> centerList)
			throws UserDefinedException {
		DiagnosticCenter diagnosticCenter;
		Test test;
		for (Iterator<DiagnosticCenter> iterator = centerList.iterator(); iterator.hasNext();) {
			diagnosticCenter = iterator.next();
			if (diagnosticCenter.getCenterId().equals(centerId)) {
				for (Iterator<Test> iteratorTestList = diagnosticCenter.getListOfTests().iterator(); iteratorTestList
						.hasNext();) {
					test = iteratorTestList.next();
					if (test.getTestId().equals(removeTestId)) {
						return removeTestId;
					}
				}
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidTestId);
	}

//	public static void validateTestIndex(String selectTestIndex, int selectCenterIndex, List<Test> testList)
//			throws UserDefinedException {
//		String regex = "^[0-9]*$";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(selectTestIndex);
//		if (!matcher.matches()) {
//			throw new UserDefinedException("Enter a numeric choice!");
//		} else {
//			if (Integer.parseInt(selectTestIndex) >= testList.size() || (Integer.parseInt(selectTestIndex) < 0)) {
//				throw new UserDefinedException("Enter a proper test choice");
//			}
//		}
//	}

	public LocalDateTime validateDateTime(String dateString) throws UserDefinedException {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime userDateTime=null;
		LocalDate userDate = null;
		LocalTime userTime = null;
		LocalTime closeTime = LocalTime.parse("20:00", timeFormat);
		LocalTime openTime = LocalTime.parse("10:00", timeFormat);

		userDateTime = LocalDateTime.parse(dateString, dateFormat);
		System.out.println("userInputDateTime"+userDateTime);
		userDate = userDateTime.toLocalDate();
		userTime = userDateTime.toLocalTime();
			
		LocalDate currentDate = LocalDate.now();
		if(userDate.isBefore(currentDate)) {
			throw new UserDefinedException(UserErrorMessage.userErrorPastDate);
		}
		else if(userTime.isAfter(closeTime) || userTime.isBefore(openTime)) {
			throw new UserDefinedException(UserErrorMessage.userErrorNonWorkingHours);
		}
		else if(null == (userDateTime=LocalDateTime.parse(dateString, dateFormat))){
			throw new UserDefinedException(UserErrorMessage.userErrorInvalidDateFormat);
		}
		return userDateTime;
	}
	
//	public LocalTime validateTime(String timeString) throws UserDefinedException {
//		LocalTime userInputTime=null;
//		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
//		LocalTime closeTime = LocalTime.parse("20:00", timeFormat);
//		LocalTime openTime = LocalTime.parse("10:00", timeFormat);
//		try {
//			userInputTime = LocalTime.parse(timeString, timeFormat);
//			if(userInputTime.isAfter(closeTime) || userInputTime.isBefore(openTime)) {
//				throw new UserDefinedException(UserErrorMessage.userErrorNonWorkingHours);
//			}
//			
//		}catch(Exception parseException) {
//			throw new UserDefinedException(UserErrorMessage.userErrorInvalidTimeFormat);
//		}
//		return userInputTime;
//	}

	public User validateUserId(String userId) throws UserDefinedException {
		User user = null;
		List<User> userList = userDao.getUserList();
		Iterator<User> userListIterator = userList.iterator();
		while(userListIterator.hasNext()) {
			user = userListIterator.next();
			if(user.getUserId().equals(userId)) {
				return user;
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidUserId);
	}

	public String validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)
			throws UserDefinedException {
		Appointment appointment = null;
		Iterator<Appointment> appointmentListIterator = listOfAppointment.iterator();
		while (appointmentListIterator.hasNext()) {
			appointment = appointmentListIterator.next();
			if (appointment.getAppointmentId().equals(appointmentId)) {
				return appointmentId;
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidAppointmentId);
	}

	public Appointment addAppointment(Appointment appointment, String centerId, List<DiagnosticCenter> centerList) {
		DiagnosticCenter diagnosticCenter = null;
		Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
		while(diagnosticCenterIterator.hasNext()) {
			diagnosticCenter = diagnosticCenterIterator.next();
			if(diagnosticCenter.getCenterId().equals(centerId)) {
				diagnosticCenter.getListOfAppointments().add(appointment);
			}
		}
		return appointment;
	}

	public List<Appointment> getAppointmentList(User user) {
		//Local Variables
		DiagnosticCenter diagnosticCenter;
		Appointment appointment=null;
		Iterator<Appointment> appointmentIterator = null;
		
		//create a list to send to UI of user appointments
		List<Appointment> userAppointmentList = new ArrayList<Appointment>();
		
		//get list of all centers
		List<DiagnosticCenter> centerList = userDao.getCenterList();
		
		//Traverse through all centers and check each appointment inside the lists of all centers
		//If User id matches, add that appointment object to the userAppointmentList to be sent to UI
		Iterator<DiagnosticCenter> diagnosticCenterIterator = centerList.iterator();
		while(diagnosticCenterIterator.hasNext()) {
			diagnosticCenter = diagnosticCenterIterator.next();
			appointmentIterator = diagnosticCenter.getListOfAppointments().iterator();
			while(appointmentIterator.hasNext()) {
				appointment = appointmentIterator.next();
				if(appointment.getUser().equals(user)) {
					userAppointmentList.add(appointment);
				}
			}
		}
		return userAppointmentList;
	}

}
