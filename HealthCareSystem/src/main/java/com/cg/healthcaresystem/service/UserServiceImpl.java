package com.cg.healthcaresystem.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	private UserDao dao = new UserDaoImpl();

	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		return dao.addCenter(center);
	}

	public boolean removeCenter(String centerid) {

		return dao.removeCenter(centerid);
	}

	public Test addTest(String name, Test test) {
		return dao.addTest(name, test);

	}

	public boolean removeTest(String removeCenterId, String removeTestId) {
		// TODO Auto-generated method stub
		return dao.removeTest(removeCenterId, removeTestId);

	}

	public String register(User user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

	public List<DiagnosticCenter> getCenterList() {
		return dao.getCenterList();
	}

	public boolean setCenterList(List<DiagnosticCenter> centerList) {
		return dao.setCenterList(centerList);
	}

	public List<User> getUserList() {
		return dao.getUserList();
	}

	public boolean setUserList(List<User> li) {
		return dao.setUserList(li);
	}

	public static void validatePassword(String userPassword) throws UserDefinedException {
		// TODO Auto-generated method stub
		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userPassword);
		if (matcher.matches() == false) {
			throw new UserDefinedException(UserErrorMessage.userErrorPassword);
		}

	}

	public static void validateName(String userName) throws UserDefinedException {
		// TODO Auto-generated method stub
		String regex = "^[A-Z].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userName);
		if (matcher.matches() == false) {
			throw new UserDefinedException("Enter a valid name.\n Name should start with a capital letter");
		}

	}

	public static void validateContactNo(String userContactNo) throws UserDefinedException {
		// TODO Auto-generated method stub
		String regex = "^[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher((CharSequence) userContactNo);
		if (matcher.matches() == false) {
			throw new UserDefinedException("Enter valid contact number");
		} else {
			if (userContactNo.length() != 10) {
				throw new UserDefinedException("Contact number should have 10 digits");
			}
		}

	}

	public static void validateEmail(String userEmail) throws UserDefinedException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userEmail);
		if (matcher.matches() == false) {
			throw new UserDefinedException("Enter valid email id.");
		}

	}

	public static void validateAge(Integer age) throws UserDefinedException {
		// TODO Auto-generated method stub
		if (age <= 0 && age > 110) {
			throw new UserDefinedException("Enter valid age");
		}

	}

	public static void validateGender(String gender) throws UserDefinedException {
		// TODO Auto-generated method stub
		if (!(gender.equals("M") || gender.equals("F") || gender.equals("O"))) {
			throw new UserDefinedException("Enter valid gender");
		}

	}

	public static void validateCenterId(String centerId, List<DiagnosticCenter> centerList)
			throws UserDefinedException {
		int i = 0;
		for (i = 0; i < centerList.size(); i++) {
			if (centerList.get(i).getCenterId().equals(centerId)) {
				break;
			}
		}
		if (i == centerList.size())
			throw new UserDefinedException("Enter a proper center id");
	}

	public static void validateTestid(String removeTestId, String centerId, List<DiagnosticCenter> centerList)
			throws UserDefinedException {
		int i = 0;
		int j = 0;
		for (i = 0; i < centerList.size(); i++) {
			if (centerList.get(i).getCenterId().equals(centerId)) {
				List<Test> testList = centerList.get(i).getListOfTests();
				for (j = 0; j < testList.size(); j++) {
					if (testList.get(j).getTestId().equals(removeTestId)) {
						break;
					}
				}
				if (j == testList.size()) {
					throw new UserDefinedException("Enter correct test id");
				}
			}
		}
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

	public static void validateAppointmentId(String appointmentid, List<Appointment> listofappointment)
			throws UserDefinedException {
		int i = 0;
		for (i = 0; i < listofappointment.size(); i++) {
			if (listofappointment.get(i).getAppointmentId().equals(appointmentid)) {
				if (listofappointment.get(i).isApproved() == true) {
					throw new UserDefinedException("Appointment is already approved!");
				}
				break;
			}
		}
		if (i == listofappointment.size())
			throw new UserDefinedException("Enter a proper appointmentId");

	}

}
