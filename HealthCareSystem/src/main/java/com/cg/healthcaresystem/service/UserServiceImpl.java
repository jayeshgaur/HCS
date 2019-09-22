package com.cg.healthcaresystem.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

	public DiagnosticCenter addCenter(DiagnosticCenter center) throws UserDefinedException {
		return userDao.addCenter(center);
	}

	public boolean removeCenter(BigInteger centerId) {
		return userDao.removeCenter(centerId);
	}

	public Test addTest(BigInteger centerId, Test test) throws UserDefinedException {
		/*
		 * List<DiagnosticCenter> centerList = userDao.getCenterList(); DiagnosticCenter
		 * diagnosticCenter = null; Iterator<DiagnosticCenter> centerListIterator =
		 * centerList.iterator(); while (centerListIterator.hasNext()) {
		 * diagnosticCenter = centerListIterator.next(); if
		 * (diagnosticCenter.getCenterId().equals(centerId)) {
		 * diagnosticCenter.getListOfTests().add(test); return test; } } throw new
		 * UserDefinedException(UserErrorMessage.userErrorAddTestFailed);
		 */
		return userDao.addTest(centerId, test);
	}

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException {
		return userDao.removeTest(removeCenterId, removeTestId);
	}

	public BigInteger register(User user) {
		return userDao.register(user);
	}

	public List<DiagnosticCenter> getCenterList() {
		return userDao.getCenterList();
	}

//	public boolean setCenterList(List<DiagnosticCenter> centerList) {
	// return userDao.setCenterList(centerList);
//	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}

	public boolean setUserList(List<User> li) {
		return userDao.setUserList(li);
	}

	/*
	 * public boolean approveAppointment(String appointmentId, List<Appointment>
	 * appointmentList) { int status = 0; Iterator<Appointment>
	 * appointmentListIterator = appointmentList.iterator(); Appointment
	 * appointment; while (appointmentListIterator.hasNext()) { appointment =
	 * appointmentListIterator.next(); if
	 * (appointment.getAppointmentid().equals(appointmentId)) { status =
	 * appointment.setApproved(1); } } return status; }
	 */

	public String validatePassword(String userPassword) throws UserDefinedException {
		if (userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
			return userPassword;
		}
		throw new UserDefinedException(UserErrorMessage.userErrorSecret);
	}

	public String validateName(String userName) throws UserDefinedException {

		if (userName.matches("^[A-Z].*")) {
			return userName;
		}
		throw new UserDefinedException(UserErrorMessage.userErrorUserName);
	}

	public String validateContactNo(String userContactNo) throws UserDefinedException {
		if (userContactNo.matches("^[0-9]+")) {
			if (userContactNo.length() != 10) {
				throw new UserDefinedException(UserErrorMessage.userErrorContactNoLength);
			} else {
				return userContactNo;
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorStringContactNo);
	}

	public String validateEmail(String userEmail) throws UserDefinedException {
		if (userEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
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

	public BigInteger validateCenterId(String centerId, List<DiagnosticCenter> centerList) throws UserDefinedException {
		if (centerId.matches("^[0-9]+")) {
			for (Iterator<DiagnosticCenter> iterator = centerList.iterator(); iterator.hasNext();) {
				DiagnosticCenter diagnosticCenter = iterator.next();
				if (diagnosticCenter.getCenterId().compareTo(new BigInteger(centerId)) == 0)
					return new BigInteger(centerId);

			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidCenterId);
	}

	public BigInteger validateTestId(String testId, List<Test> testList) throws UserDefinedException {
		if (testId.matches("^[0-9]+")) {
			Iterator<Test> testIterator = testList.iterator();
			while (testIterator.hasNext()) {
				Test test = testIterator.next();
				if (test.getTestId().compareTo(new BigInteger(testId)) == 0) {
					return test.getTestId();
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
		LocalDateTime userDateTime = null;
		LocalDate userDate = null;
		LocalTime userTime = null;
		LocalTime closeTime = LocalTime.parse("20:00", timeFormat);
		LocalTime openTime = LocalTime.parse("10:00", timeFormat);

		userDateTime = LocalDateTime.parse(dateString, dateFormat);
		userDate = userDateTime.toLocalDate();
		userTime = userDateTime.toLocalTime();

		LocalDate currentDate = LocalDate.now();
		if (userDate.isBefore(currentDate)) {
			throw new UserDefinedException(UserErrorMessage.userErrorPastDate);
		} else if (userTime.isAfter(closeTime) || userTime.isBefore(openTime)) {
			throw new UserDefinedException(UserErrorMessage.userErrorNonWorkingHours);
		} else if (null == (userDateTime = LocalDateTime.parse(dateString, dateFormat))) {
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

	public BigInteger validateUserId(String userId) throws UserDefinedException {
		if(userId.matches("^[0-9]+")) {
		List<User> listOfUser = userDao.getUserList();
		Iterator<User> userIterator = listOfUser.iterator();
		while (userIterator.hasNext()) {
			User user = userIterator.next();
			if (user.getUserId().compareTo(new BigInteger(userId))== 0) {
				return new BigInteger(userId);
			}

		}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidUserId);
	}

	public BigInteger validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)
			throws UserDefinedException {
		if(appointmentId.matches("^[0-9]+"))
		{
		Appointment appointment = null;
		Iterator<Appointment> appointmentListIterator = listOfAppointment.iterator();
		while (appointmentListIterator.hasNext()) {
			appointment = appointmentListIterator.next();
			if ((appointment.getAppointmentId().compareTo(new BigInteger(appointmentId))==0) && (appointment.getAppointmentstatus()==0)) {
				return new BigInteger(appointmentId);
			}
		}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidAppointmentId);
	}

	public Appointment addAppointment(Appointment appointment) {
		return userDao.addAppointment(appointment);
	}

	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
		// TODO Auto-generated method stub
		return userDao.getListOfTests(centerId);
	}

	@Override
	public List getAppointmentList(BigInteger userId) {
		return userDao.getAppointmentList(userId);
	}

	@Override
	public boolean approveAppointment(BigInteger appointmentId) {
		return userDao.approveAppointment(appointmentId);
	}
	
	@Override
	public List<Appointment> getListOfAppointments(){
		return userDao.getListOfAppointments();
	}

}
