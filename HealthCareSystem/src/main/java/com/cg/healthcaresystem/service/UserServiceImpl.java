package com.cg.healthcaresystem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static void validatePassword(String userPassword) throws UserDefinedException {
		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userPassword);
		if (matcher.matches() == false) {
			throw new UserDefinedException(UserErrorMessage.userErrorPassword);
		}
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
		
//		String regex = "^[0-9]+";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher((CharSequence) userContactNo);
//		if (matcher.matches() == false) {
//			throw new UserDefinedException(UserErrorMessage.userErrorStringContactNo);
//		} else {
//			if (userContactNo.length() != 10) {
//				throw new UserDefinedException(UserErrorMessage.userErrorContactNoLength);
//			}
//		}
//		return userContactNo;
	}

	public static void validateEmail(String userEmail) throws UserDefinedException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userEmail);
		if (matcher.matches() == false) {
			throw new UserDefinedException(UserErrorMessage.userErrorEmailId);
		}

	}

	public static void validateAge(Integer age) throws UserDefinedException {
		if (age <= 0 && age > 110) {
			throw new UserDefinedException(UserErrorMessage.userErrorUserAge);
		}

	}

	public static void validateGender(String gender) throws UserDefinedException {
		if (!(gender.equals("M") || gender.equals("F") || gender.equals("O"))) {
			throw new UserDefinedException(UserErrorMessage.userErrorUserGender);
		}

	}

	public String validateCenterId(String centerId, List<DiagnosticCenter> centerList) throws UserDefinedException {
		for (Iterator<DiagnosticCenter> iterator = centerList.iterator(); iterator.hasNext();) {
			DiagnosticCenter diagnosticCenter = iterator.next();
			if (diagnosticCenter.getCenterId().equals(centerId))
				return centerId;

		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidCenterId);
	}

	public String validateTestid(String removeTestId, String centerId, List<DiagnosticCenter> centerList)
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

	public static void validateCenterIndex(String selectCenterIndex, List<DiagnosticCenter> centerList1)
			throws UserDefinedException {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(selectCenterIndex);
		if (!matcher.matches()) {
			throw new UserDefinedException("Enter a numeric choice!");
		} else {
			if (Integer.parseInt(selectCenterIndex) >= centerList1.size()
					|| (Integer.parseInt(selectCenterIndex) < 0)) {
				throw new UserDefinedException("Enter a proper choice");
			}
		}
	}

	public static void validateTestIndex(String selectTestIndex, int selectCenterIndex, List<Test> testList)
			throws UserDefinedException {
		String regex = "^[0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(selectTestIndex);
		if (!matcher.matches()) {
			throw new UserDefinedException("Enter a numeric choice!");
		} else {
			if (Integer.parseInt(selectTestIndex) >= testList.size() || (Integer.parseInt(selectTestIndex) < 0)) {
				throw new UserDefinedException("Enter a proper test choice");
			}
		}
	}

	public static void validateDate(String dateString) throws UserDefinedException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy H:m:s");
		Date date = format.parse(dateString);
		Date currentDate = new Date();
		if (date.before(currentDate)) {
			throw new UserDefinedException("You cannot  book an appointment in the past!!");
		}
	}

	public static void validateUserId(String userId, List<User> userList) throws UserDefinedException {
		int i = 0;
		for (i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUserId().equals(userId)) {
				break;
			}
		}
		if (i == userList.size()) {
			throw new UserDefinedException(
					"Invalid User ID. Make sure you are entering the exact id given after registration!");
		}
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

}
