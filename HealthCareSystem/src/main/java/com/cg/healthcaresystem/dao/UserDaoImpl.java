package com.cg.healthcaresystem.dao;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;
import com.cg.healthcaresystem.util.EntityManagerUtil;

import java.math.BigInteger;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UserDaoImpl implements UserDao {
	
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
	
	static {
		EntityManagerUtil.initialize();
	}

	@Override
	public DiagnosticCenter addCenter(DiagnosticCenter center){

		entityTransaction.begin();
		entityManager.persist(center);
		entityTransaction.commit();
		return center;
	}

	@Override
	public boolean removeCenter(BigInteger centerId) {
		entityTransaction.begin();
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		center.setDeleted(true);
		entityTransaction.commit();
		return true;
	}

	@Override
	public Test addTest(BigInteger centerId, Test test) {
		entityTransaction.begin();
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		center.getListOfTests().add(test);
		entityManager.persist(test);
		entityTransaction.commit();
		return test;
	}

	@Override
	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException {
		entityTransaction.begin();
		Test test = entityManager.find(Test.class, removeTestId);
		test.setDeleted(true);
		entityTransaction.commit();
		return true;
	}

	@Override
	public Appointment addAppointment(Appointment appointment) {
		entityTransaction.begin();
		entityManager.persist(appointment);
		entityTransaction.commit();
		return appointment;
	}

	@Override
	public BigInteger register(User user) {
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		return user.getUserId();
	}

	@Override
	public List<DiagnosticCenter> getCenterList() {
		Query query = entityManager.createQuery("FROM DiagnosticCenter WHERE isDeleted = :false");
		query.setParameter("false", false);
		@SuppressWarnings("unchecked")
		List<DiagnosticCenter> centerList =  query.getResultList();
		return centerList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList() {
		Query query = entityManager.createQuery("FROM User");
		List<User> userList = query.getResultList();
		return userList;
	}

	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
		Query query = entityManager.createQuery("FROM Test WHERE center_id_fk = :centerId AND isDeleted = :false");
		query.setParameter("false", false);
		query.setParameter("centerId", centerId);
		@SuppressWarnings("unchecked")
		List<Test> listOfTests = query.getResultList();
		return listOfTests;
	}

	@Override
	public List<Appointment> getAppointmentList(BigInteger userId) {
		User user = entityManager.find(User.class, userId);
		Query query = entityManager.createQuery("FROM Appointment WHERE user = :userObject");
		query.setParameter("userObject", user);
		@SuppressWarnings("unchecked")
		List<Appointment> userAppointmentList = query.getResultList();
		return userAppointmentList;
	}

	@Override
	public boolean approveAppointment(BigInteger appointmentId) {
		entityTransaction.begin();
		Appointment appointment = entityManager.find(Appointment.class, appointmentId);
		appointment.setAppointmentstatus(1);
		entityTransaction.commit();
		return true;
	}



	@Override
	public DiagnosticCenter findCenter(BigInteger centerId) {
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
				return center;
	}

	@Override
	public User findUser(BigInteger userId) {
		User user = entityManager.find(User.class, userId);
		return user;
	}

	@Override
	public Test findTest(BigInteger testId) {
		Test test = entityManager.find(Test.class, testId);
				return test;
	}

	@Override
	public List<Appointment> getCenterAppointmentList(BigInteger centerId) {
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		Query query = entityManager.createQuery("FROM Appointment WHERE center = :ID AND appointmentStatus = :status");
		query.setParameter("ID",center);
		query.setParameter("status",0);
		@SuppressWarnings("unchecked")
		List<Appointment> appointmentList = query.getResultList();
		return appointmentList;
	}

}
