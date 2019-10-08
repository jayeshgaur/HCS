package com.cg.healthcaresystembootmvc.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.healthcaresystembootmvc.dao.CenterRepository;
import com.cg.healthcaresystembootmvc.dao.TestRepository;
import com.cg.healthcaresystembootmvc.dao.UserDao;
import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;
import com.cg.healthcaresystembootmvc.dto.Test;
import com.cg.healthcaresystembootmvc.dto.User;
import com.cg.healthcaresystembootmvc.exception.UserDefinedException;
import com.cg.healthcaresystembootmvc.exception.UserErrorMessage;
import com.cg.healthcaresystembootmvc.exception.ValidationException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TestRepository testrepository;
	
	@Autowired
	private CenterRepository centerrepository;
	

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		return userDao.addCenter(center);
	}

	public boolean removeCenter(BigInteger centerId) {
		return userDao.removeCenter(centerId);
	}

    //Add Test
	public Test addTest(BigInteger centerId, Test test) {
		DiagnosticCenter center=centerrepository.findById(centerId).get();
		center.getListOfTests().add(test);
		return testrepository.save(test);
	}

	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) {
		
		    DiagnosticCenter center=centerrepository.findById(removeCenterId).get();
		    Test test=testrepository.findById(removeTestId).get();
		    center.getListOfTests().remove(test);
			testrepository.deleteById(removeTestId);
			return true;
	}

	public BigInteger register(User user) {
		return userDao.register(user);
	}

	public List<DiagnosticCenter> getCenterList() {
		return centerrepository.findAll();
	}

//	public boolean setCenterList(List<DiagnosticCenter> centerList) {
	// return userDao.setCenterList(centerList);
//	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}

	public BigInteger userLogin(String email, String password) {
		BigInteger userId = userDao.getUserLogin(email, password);
		return userId;
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

	public String validatePassword(String userPassword) throws ValidationException {
		if (userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
			return userPassword;
		}
		throw new ValidationException(UserErrorMessage.userErrorSecret);
	}

	public String validateName(String userName) throws ValidationException {

		if (userName.matches("^[A-Z].*")) {
			return userName;
		}
		throw new ValidationException(UserErrorMessage.userErrorUserName);
	}

	public String validateContactNo(String userContactNo) throws ValidationException {
		if (userContactNo.matches("^[0-9]+")) {
			if (userContactNo.length() != 10) {
				throw new ValidationException(UserErrorMessage.userErrorContactNoLength);
			} else {
				return userContactNo;
			}
		}
		throw new ValidationException(UserErrorMessage.userErrorStringContactNo);
	}

	public String validateEmail(String userEmail) throws ValidationException {
		if (userEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			return userEmail;
		}
		throw new ValidationException(UserErrorMessage.userErrorEmailId);
	}

	public Integer validateAge(Integer age) throws ValidationException {
		if (age < 5 && age > 110) {
			throw new ValidationException(UserErrorMessage.userErrorUserAge);
		}
		return age;
	}

	public String validateGender(String gender) throws ValidationException {
		if (!(gender.equals("M") || gender.equals("F") || gender.equals("O"))) {
			throw new ValidationException(UserErrorMessage.userErrorUserGender);
		}
		return gender;

	}

	public BigInteger validateCenterId(String centerId, List<DiagnosticCenter> centerList) throws ValidationException {
		if (centerId.matches("^[0-9]+")) {
			for (Iterator<DiagnosticCenter> iterator = centerList.iterator(); iterator.hasNext();) {
				DiagnosticCenter diagnosticCenter = iterator.next();
				if (diagnosticCenter.getCenterId().compareTo(new BigInteger(centerId)) == 0)
					return new BigInteger(centerId);

			}
		}
		throw new ValidationException(UserErrorMessage.userErrorInvalidCenterId);
	}

	public BigInteger validateTestId(String testId, List<Test> testList) throws ValidationException {
		if (testId.matches("^[0-9]+")) {
			Iterator<Test> testIterator = testList.iterator();
			while (testIterator.hasNext()) {
				Test test = testIterator.next();
				if (test.getTestId().compareTo(new BigInteger(testId)) == 0) {
					return test.getTestId();
				}
			}
		}
		throw new ValidationException(UserErrorMessage.userErrorInvalidTestId);
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

	public LocalDateTime validateDateTime(String dateString) throws ValidationException {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
		LocalDateTime userDateTime = null;
		LocalDate userDate = null;
		LocalTime userTime = null;
		LocalTime closeTime = LocalTime.parse("20:00", timeFormat);
		LocalTime openTime = LocalTime.parse("10:00", timeFormat);
		try {
			userDateTime = LocalDateTime.parse(dateString, dateFormat);
			userDate = userDateTime.toLocalDate();
			userTime = userDateTime.toLocalTime();
		} catch (Exception exception) {
			throw new ValidationException(UserErrorMessage.userErrorInvalidDateFormat);
		}
		LocalDate currentDate = LocalDate.now();
		if (userDate.isBefore(currentDate)) {
			throw new ValidationException(UserErrorMessage.userErrorPastDate);
		} else if (userTime.isAfter(closeTime) || userTime.isBefore(openTime)) {
			throw new ValidationException(UserErrorMessage.userErrorNonWorkingHours);
		} else if (null == (userDateTime = LocalDateTime.parse(dateString, dateFormat))) {
			throw new ValidationException(UserErrorMessage.userErrorInvalidDateFormat);
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
		if (userId.matches("^[0-9]+")) {
			List<User> listOfUser = userDao.getUserList();
			Iterator<User> userIterator = listOfUser.iterator();
			while (userIterator.hasNext()) {
				User user = userIterator.next();
				if (user.getUserId().compareTo(new BigInteger(userId)) == 0) {
					return new BigInteger(userId);
				}

			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorInvalidUserId);
	}

	public BigInteger validateAppointmentId(String appointmentId, List<Appointment> listOfAppointment)
			throws ValidationException {
		if (appointmentId.matches("^[0-9]+")) {
			Appointment appointment = null;
			Iterator<Appointment> appointmentListIterator = listOfAppointment.iterator();
			while (appointmentListIterator.hasNext()) {
				appointment = appointmentListIterator.next();
				if ((appointment.getAppointmentId().compareTo(new BigInteger(appointmentId)) == 0)
						&& (appointment.getAppointmentStatus() == 0)) {
					return new BigInteger(appointmentId);
				}
			}
		}
		throw new ValidationException(UserErrorMessage.userErrorInvalidAppointmentId);
	}

	public Appointment addAppointment(Appointment appointment) {
		/*
		 * appointment.setCenter(userDao.findCenter(centerId));
		 * appointment.setTest(userDao.findTest(testId));
		 * appointment.setUser(userDao.findUser(userId));
		 * appointment.setDateTime(dateTime); appointment.setAppointmentstatus(0);
		 */
		return userDao.addAppointment(appointment);
	}

	//Lists add the test of the given center
	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
		DiagnosticCenter center= centerrepository.findById(centerId).get();
		List<Test> testList=center.getListOfTests();
		return testList;
	}

	@Override
	public List<Appointment> getAppointmentList(BigInteger userId) {
		return userDao.getAppointmentList(userId);
	}

	@Override
	public boolean approveAppointment(BigInteger appointmentId) {
		return userDao.approveAppointment(appointmentId);
	}

	@Override
	public List<Appointment> getCenterAppointmentList(BigInteger centerId) {
		return userDao.getCenterAppointmentList(centerId);
	}

	@Override
	public DiagnosticCenter findCenter(BigInteger centerId) {
		return userDao.findCenter(centerId);
	}

	@Override
	public Test findTest(BigInteger testId) {
		// TODO Auto-generated method stub
		return userDao.findTest(testId);
	}

	@Override
	public User findUser(BigInteger userId) {
		// TODO Auto-generated method stub
		return userDao.findUser(userId);
	}

}
