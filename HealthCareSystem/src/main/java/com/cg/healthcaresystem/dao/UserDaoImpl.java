package com.cg.healthcaresystem.dao;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.exception.UserErrorMessage;
import com.cg.healthcaresystem.util.EntityManagerUtil;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UserDaoImpl implements UserDao {
	private static EntityTransaction entityTransaction;

	static {
		EntityManagerUtil.initialize();
		entityTransaction = EntityManagerUtil.getEntityManager().getTransaction();
	}

	@Override
	public DiagnosticCenter addCenter(DiagnosticCenter center) throws UserDefinedException {

		entityTransaction.begin();
		EntityManagerUtil.getEntityManager().persist(center);
		entityTransaction.commit();
		return center;
	}

	@Override
	public boolean removeCenter(BigInteger centerId) {
		entityTransaction.begin();
		DiagnosticCenter center = EntityManagerUtil.getEntityManager().find(DiagnosticCenter.class, centerId);
		if(null!=center)
		entityTransaction.commit();
		return true;
	}

	@Override
	public Test addTest(BigInteger name, Test test) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Appointment addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiagnosticCenter> getCenterList() {
		Query query = EntityManagerUtil.getEntityManager().createQuery("FROM DiagnosticCenter");
		@SuppressWarnings("unchecked")
		List<DiagnosticCenter> centerList =  query.getResultList();
		return centerList;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setUserList(List<User> li) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAppointmentList(BigInteger userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveAppointment(BigInteger appointmentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Appointment> getListOfAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

}
