package com.cg.healthcaresystembootmvc.service;
/*
 * Author: Jayesh Gaur
 * Description: Service Class
 * Created on: October 9, 2019
 */
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

import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;
import com.cg.healthcaresystembootmvc.dto.Test;
import com.cg.healthcaresystembootmvc.dto.User;
import com.cg.healthcaresystembootmvc.exception.ExistingUserCredentialException;
import com.cg.healthcaresystembootmvc.exception.UserErrorMessage;
import com.cg.healthcaresystembootmvc.exception.ValidationException;
import com.cg.healthcaresystembootmvc.repository.AppointmentRepository;
import com.cg.healthcaresystembootmvc.repository.CenterRepository;
import com.cg.healthcaresystembootmvc.repository.TestRepository;
import com.cg.healthcaresystembootmvc.repository.UserDao;
import com.cg.healthcaresystembootmvc.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private CenterRepository centerRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		return centerRepository.save(center);
	}

	public boolean removeCenter(BigInteger centerId) {
		DiagnosticCenter center = centerRepository.findById(centerId).get();
		center.setDeleted(true);
		return true;
	}


	/*
	 * Author : Nidhi
	 * Description : This service method is adding test to the list of tests of center and saving the test using the save method of Test Repository.
	 * Created Date : 9th October,2019
	 * Input: Test
	 * Return Type : Test
	 * 
	 * */

	public Test addTest(BigInteger centerId, Test test) {
		DiagnosticCenter center = centerRepository.findById(centerId).get();
		center.getListOfTests().add(test);
		return testRepository.save(test);
	}

	/*
	 * Author : Nidhi
	 * Description : This service method is removing test from the list of tests of center and removing the test by setting isDeleted attribute of Test to true.
	 * Created Date : 9th October,2019
	 * Input: BigInteger removeCenterId, BigInteger removeTestId
	 * Return Type : boolean
	 * 
	 * */
	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws ValidationException {
		DiagnosticCenter center = centerRepository.findById(removeCenterId).get();
		if (center == null) {
			throw new ValidationException("center is not present");
		}
		Test test = testRepository.findById(removeTestId).get();
		if (test == null) {
			throw new ValidationException("test not found");
		}
		test.setDeleted(true);
		center.getListOfTests().remove(test);
		// testrepository.deleteById(removeTestId);
		return true;
	}

	/*
	 * Author: 				Jayesh Gaur 
	 * Description: 		Service method for registration. Calls the Repository save method 
	 * 							and returns the automatically generated new user Id.
	 * Exceptions thrown: 	If unique values like email or phone number already exist in
	 * 							database, exception is thrown and user is notified.
	 * Created on:			October 9, 2019
	 */
	public BigInteger register(User user) throws ExistingUserCredentialException{
		
		//validating unique database columns
		User checkUserCredentials = userRepository.findByUserEmail(user.getUserEmail());
		if(null != checkUserCredentials) {
			throw new ExistingUserCredentialException(UserErrorMessage.userErrorDuplicateEmail);
		}else {
			checkUserCredentials = null;
			checkUserCredentials = userRepository.findByContactNo(user.getContactNo());
			if(null != checkUserCredentials) {
				throw new ExistingUserCredentialException(UserErrorMessage.userErrorDuplicatePhoneNumber);
			}
		}
		//save if email and phone numbers are unique
		return userRepository.save(user).getUserId();
	}

	public List<DiagnosticCenter> getCenterList() {
		return centerRepository.getCenterList();
	}

	/*
	 * Author: 		Jayesh Gaur
	 * Description: TO BE DONE (NOT VALIDATED)
	 * Created on:  October 9, 2019
	 */
	public BigInteger userLogin(String email, String password) {
		User user = userRepository.findByUserEmailAndUserPassword(email,password);
		return user.getUserId();
	}

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

	/*
	 * Author:		 	Jayesh Gaur
	 * Description:  	Calls appointmentRepository to persist the new appointment object
	 * Created on: 		October 9, 2019
	 */
	public Appointment addAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	/*
	 * Author:		 	Jayesh Gaur
	 * Description:  	Accepts the appointment Id to be approved and marks it in the database
	 * 					Returns true on successful completion
	 * Created on: 		October 9, 2019
	 */
	@Override
	public boolean approveAppointment(BigInteger appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).get();
		appointment.setAppointmentStatus(1);
		return true;
	}

	/*
	 * Author:		 	Jayesh Gaur
	 * Description:  	Returns the list of appointments which are not approved in the center
	 * 					corresponding to the center id received in input
	 * Created on: 		October 9, 2019
	 */
	@Override
	public List<Appointment> getCenterAppointmentList(BigInteger centerId) {
		DiagnosticCenter center = centerRepository.findById(centerId).get();
		return appointmentRepository.findByCenterAndAppointmentStatus(center, 0);
	}

	/*
	 * Author:		 	Jayesh Gaur
	 * Description:  	Returns the center object corresponding to 
	 * 					the center Id received in input
	 * Created on: 		October 9, 2019
	 */
	@Override
	public DiagnosticCenter findCenter(BigInteger centerId) {
		return centerRepository.findById(centerId).get();
	}

	/*
	 * Author:		 	Jayesh Gaur
	 * Description:  	Returns the user object corresponding to 
	 * 					the user Id received in input
	 * Created on: 		October 9, 2019
	 */
	@Override
	public User findUser(BigInteger userId) {
		return userRepository.findById(userId).get();
	}
	

	@Override
	public List<Appointment> getAppointmentList(BigInteger userId) {
		return userDao.getAppointmentList(userId);
	}
	
	@Override
	public Test findTest(BigInteger testId) {
		return testRepository.findById(testId).get();
	}
	
	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
			DiagnosticCenter center = centerRepository.findById(centerId).get();
			List<Test> testList = center.getListOfTests();
			return testList;
		}

}
