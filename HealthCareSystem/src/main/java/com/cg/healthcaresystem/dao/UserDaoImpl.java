package com.cg.healthcaresystem.dao;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.exception.UserErrorMessage;
import com.cg.healthcaresystem.util.DbUtil;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UserDaoImpl implements UserDao {

	List<User> userList = new ArrayList<User>();
	DiagnosticCenter dignosticcenter = null;
	Test test = null;
	private static Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private static Logger myLogger;
	static {

		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "\\src\\main\\resources\\" + "";
		System.out.println("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");

		try {
			connection = DbUtil.getConnection();
			myLogger.info("connection obtained.....");
		} catch (UserDefinedException e) {
			myLogger.error("connection not established at EmployeeDao: " + e);
		}
	}

	public DiagnosticCenter addCenter(DiagnosticCenter center) {

		DiagnosticCenter newCenter = null;

		String sql = "insert into Center(center_name,center_address,center_contact_no,isEmpty) values(?,?,?,?)";
		try {
			newCenter = new DiagnosticCenter(center.getCenterName(), center.getCenterAddress(),
					center.getCenterContactNo());
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(3, center.getCenterContactNo().longValue());
			ps.setString(1, center.getCenterName());
			ps.setString(2, center.getCenterAddress());
			ps.setInt(4, 1);

			int noOfRecords = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				newCenter.setCenterId(BigInteger.valueOf(rs.getLong(1)));
			}
			if (noOfRecords <= 0) {

			}
		} catch (Exception exception) {
			myLogger.error("Error at addCenter Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at addCenter Dao method:" + e.getMessage());
				}
			}
		}

		return newCenter;

	}

	public boolean removeCenter(BigInteger centerId) {
		boolean check = false;
		String sql = "update Test SET isEmpty=0 where center_id=?";
		String sql1 = "update Center set isEmpty=0 where center_id=?";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, centerId.longValue());
			ps.executeUpdate();
			ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, centerId.longValue());
			int changedCenterRecords = ps.executeUpdate();
			if (changedCenterRecords < 1) {
				throw new UserDefinedException(UserErrorMessage.userErrorNoCenterDeleted);
			} else {
				check = true;
			}

		} catch (Exception exception) {
			myLogger.error("Error at removeCenter Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at removeCenter Dao method:" + e.getMessage());
				}
			}
		}
		return check;

	}

	public Test addTest(BigInteger centerId, Test test) {
		Test newTest = null;
		String sql = "insert into Test(test_name,center_id,isEmpty) values(?,?,?)";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, test.getTestName());
			ps.setLong(2, centerId.longValue());
			ps.setInt(3, 1);
			int noOfRecords = ps.executeUpdate();
			if (noOfRecords <= 0) {
				throw new UserDefinedException(UserErrorMessage.userErrorAddTestFailed);
			}
			newTest = new Test(test.getTestName());
			rs = ps.getGeneratedKeys();
			if (rs != null) {
				rs.next();
			}
			newTest.setTestId(BigInteger.valueOf(rs.getLong(1)));
		} catch (Exception exception) {
			myLogger.error("Error at addTest Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at addTest Dao method:" + e.getMessage());
				}
			}
		}
		return newTest;
	}

//	public boolean removeTest(BigInteger removeCenterId, String removeTestId) {
//		List<Test> tempTestList = new ArrayList<Test>();
//		for (int i = 0; i < centerList.size(); i++) {
//			if (centerList.get(i).getCenterId().equals(removeCenterId)) {
//				tempTestList = centerList.get(i).getListOfTests();
//				for (int j = 0; j < tempTestList.size(); j++) {
//					if (tempTestList.get(j).getTestId().equals(removeTestId)) {
//						tempTestList.remove(j);
//					}
//				}
//				centerList.get(i).setListOfTests(tempTestList);
//				return true;
//			}
//		}
//		return false;
//
//		// int flag=0;
////		Iterator itr=centerList.iterator();
////		while(itr.hasNext())
////		{
////			DiagnosticCenter obj=(DiagnosticCenter) itr.next();
////			if(obj.getCenterName().equals(centername))
////			{
////				List<Test> testList=obj.getListOfTests();
////				Iterator testlistitr=testList.iterator();
////				while(testlistitr.hasNext())
////				{
////					Test testobj=(Test)itr.next();
////					if(testobj.getTestName()==testname)
////					{
////						testList.remove(testobj);
////						flag++;
////						break;
////					}
////				}
////			}
////		}
////		if(flag==0)
////		return false;
////		else
////			return true;
//
//	}

	public List<DiagnosticCenter> getCenterList() {
		List<DiagnosticCenter> centerList = new LinkedList<DiagnosticCenter>();
		String sql = "select * from Center where isEmpty=1";
		try {

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				dignosticcenter = new DiagnosticCenter();
				dignosticcenter.setCenterId(BigInteger.valueOf(rs.getLong(1)));
				dignosticcenter.setCenterName(rs.getString(2));
				dignosticcenter.setCenterAddress(rs.getString(3));
				dignosticcenter.setCenterContactNo(BigInteger.valueOf(rs.getLong(4)));

				centerList.add(dignosticcenter);

			}

		} catch (Exception exception) {
			myLogger.error("Error at listCenter Dao method, GETcENTERLIST: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at istCenterr Dao method:" + e.getMessage());
				}
			}
		}

		return centerList;
	}

	// public boolean setCenterList(List<DiagnosticCenter> centerList) {
	// return (null != (UserDaoImpl.centerList = centerList));
	// }

	// RegisteredUserList
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		String sql = "select * from User";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(BigInteger.valueOf(rs.getLong(1)));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setContactNo(BigInteger.valueOf(rs.getLong(4)));
				user.setUserRole(rs.getString(5));
				user.setUserEmail(rs.getString(6));
				user.setAge(rs.getInt(7));
				user.setGender(rs.getString(8));
				userList.add(user);
			}
		} catch (Exception exception) {
			myLogger.error("Error at listUser Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at listUser Dao method:" + e.getMessage());
				}
			}
		}
		return userList;
	}

	public boolean setUserList(List<User> userList) {
		this.userList = userList;
		return true;
	}

	public BigInteger register(User user) {
		String sql = "insert into User(user_name,user_password,user_contact_no,user_role,user_email,user_age,user_gender,isEmpty)"
				+ "values(?,?,?,?,?,?,?,?)";
		BigInteger userId = null;
		try {

			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setLong(3, user.getContactNo().longValue());
			ps.setString(4, "User");
			ps.setString(5, user.getUserEmail());
			ps.setInt(6, user.getAge());
			ps.setString(7, user.getGender());
			ps.setInt(8, 1);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				userId = BigInteger.valueOf(rs.getLong(1));
			}

		} catch (Exception exception) {
			myLogger.error("Error at register Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at register Dao method:" + e.getMessage());
				}
			}
		}
		return userId;
	}

	@Override
	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException {
		String sql = "update Test set isEmpty=0  where test_id=? AND center_id=?";

		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, removeTestId.longValue());
			ps.setLong(2, removeCenterId.longValue());
			int noOfRecords = ps.executeUpdate();
			if (noOfRecords > 0) {
				return true;
			}
		} catch (SQLException exception) {
			myLogger.error("Error at remove Test Dao SQL Exception" + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at remove Test Dao method: closing connection" + e.getMessage());
				}
			}
		}
		throw new UserDefinedException(UserErrorMessage.userErrorNoTestDeleted);
	}

	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
		List<Test> testList = new ArrayList<Test>();
		String sql = "select * from Test where center_id=? AND isEmpty=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, centerId.longValue());
			ps.setInt(2, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				test = new Test();
				test.setTestId(BigInteger.valueOf(rs.getLong(1)));
				test.setTestName(rs.getString(2));
				testList.add(test);
			}
		} catch (Exception exception) {
			myLogger.error("Error at listTest Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at listTest Dao method:" + e.getMessage());
				}
			}
		}
		return testList;
	}

	@Override
	public Appointment addAppointment(Appointment appointment) {
		String sql = "insert into Appointment(center_id,test_id,user_id,appointment_status,appointment_date_time,isEmpty) values(?,?,?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, appointment.getCenterid().longValue());
			ps.setLong(2, appointment.getTestId().longValue());
			ps.setLong(3, appointment.getUserId().longValue());
			ps.setInt(4, 0);
			ps.setTimestamp(5, Timestamp.valueOf(appointment.getDateTime()));
			ps.setInt(6, 1);
			int status = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			System.out.println(status);
			if (status > 0)
				appointment.setAppointmentId(BigInteger.valueOf(rs.getLong(1)));
		} catch (Exception exception) {
			myLogger.error("Error at addAppointment Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at addAppointment Dao method:" + e.getMessage());
				}
			}
		}
		return appointment;
	}

	public List getAppointmentList(BigInteger userId) {
		List listOfAppointment = new ArrayList();
		String sql = "Select a.appointmenmt_id,a.center_id,c.center_name,a.test_id,t.test_name, a.appointment_status,a.appointment_date_time from Appointment a join Test t ON a.test_id=t.test_id join Center c ON  a.center_id=c.center_id where a.user_id=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, userId.longValue());
			rs = ps.executeQuery();
			while (rs.next()) {
				listOfAppointment.add(BigInteger.valueOf(rs.getLong(1)));
				listOfAppointment.add(BigInteger.valueOf(rs.getLong(2)));
				listOfAppointment.add(rs.getString(3));
				listOfAppointment.add(BigInteger.valueOf(rs.getLong(4)));
				listOfAppointment.add(rs.getString(5));
				listOfAppointment.add(rs.getInt(6));
				listOfAppointment.add(rs.getTimestamp(7).toLocalDateTime());
			}

		} catch (Exception exception) {
			myLogger.error("Error at getAppointment Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					myLogger.error("Error at getAppointment Dao method:" + e.getMessage());
				}
			}
		}
		return listOfAppointment;
	}

	public boolean approveAppointment(BigInteger appointmentId) {
		boolean flag = false;
		String sql = "Update  Appointment set appointment_status=1 where appointmenmt_id=?;";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, appointmentId.longValue());
			if (ps.executeUpdate() == 1)
				flag = true;
		} catch (Exception exception) {
			myLogger.error("Error at approve appointment Dao method: " + exception.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					myLogger.error("Error at approve appointment Dao method:" + e.getMessage());
				}
			}
		}
		return flag;
	}
	
	@Override
	public List<Appointment> getListOfAppointments(){
		List<Appointment> listOfAllAppointments = new ArrayList<Appointment>();
		String sql = "SELECT * FROM Appointment";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setAppointmentId(BigInteger.valueOf(rs.getLong(1)));
				appointment.setCenterId(BigInteger.valueOf(rs.getLong(2)));
				appointment.setTestid(BigInteger.valueOf(rs.getLong(3)));
				appointment.setUserId(BigInteger.valueOf(rs.getLong(4)));
				appointment.setAppointmentstatus(rs.getInt(5));
				appointment.setDateTime(rs.getTimestamp(6).toLocalDateTime());
				appointment.setIsEmpty(rs.getInt(7));
				listOfAllAppointments.add(appointment);
			}
		}catch(SQLException sqlException) {
			myLogger.error("Error in getListOfAppointments"+sqlException.getMessage());
		}
		return listOfAllAppointments;
	}

//	@Override
//	public List<Appointment> getAppointmentList(User user) {
//		List<Appointment> listOfAppointment = new ArrayList<Appointment>();
//		String sql = "Select * from Appointment where user_id=?";
//		try {
//			ps = connection.prepareStatement(sql);
//			ps.setLong(1, user.getUserId().longValue());
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				Appointment appointment = new Appointment();
//				// appointment.getAppointmentid(BigInteger.valueOf(rs.getLong(1)));
//			}
//
//		} catch (Exception e) {
//		}
//		return null;
//	}

}
