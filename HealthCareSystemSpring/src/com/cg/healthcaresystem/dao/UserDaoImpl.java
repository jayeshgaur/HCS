package com.cg.healthcaresystem.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.healthcaresystem.dto.Appointment;
import com.cg.healthcaresystem.dto.DiagnosticCenter;
import com.cg.healthcaresystem.dto.Test;
import com.cg.healthcaresystem.dto.User;
import com.cg.healthcaresystem.exception.UserDefinedException;

@Repository
public class UserDaoImpl implements UserDao {
 
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		entityManager.persist(center);
		return center;
	}

	@Override
	public boolean removeCenter(BigInteger centerId) {
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		center.setDeleted(true);
		return true;
	}

	@Override
	public Test addTest(BigInteger centerId, Test test) {
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		center.getListOfTests().add(test);
		entityManager.persist(test);
		return test;
	}

	@Override
	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId) throws UserDefinedException {
		Test test = entityManager.find(Test.class, removeTestId);
		test.setDeleted(true);
		return true;
	}

	@Override
	public Appointment addAppointment(Appointment appointment) {
		entityManager.persist(appointment);
		return appointment;
	}

	@Override
	public BigInteger register(User user) {
		entityManager.persist(user);
		return user.getUserId();
	}

	@Override
	public List<DiagnosticCenter> getCenterList() {
	//	Query query = entityManager.createQuery("FROM DiagnosticCenter WHERE isDeleted = :false");
		TypedQuery<DiagnosticCenter> typedQuery = entityManager.createQuery("FROM DiagnosticCenter WHERE isDeleted = :false", DiagnosticCenter.class);
		typedQuery.setParameter("false", false);
		List<DiagnosticCenter> centerList = typedQuery.getResultList();
		return centerList;
	}

	@Override
	public List<User> getUserList() {
		TypedQuery<User> typedQuery = entityManager.createQuery("FROM User",User.class);
		List<User> userList = typedQuery.getResultList();
		return userList;
	}

	@Override
	public List<Test> getListOfTests(BigInteger centerId) {
		TypedQuery<Test> typedQuery = entityManager.createQuery("FROM Test WHERE center_id_fk = :centerId AND isDeleted = :false", Test.class);
		typedQuery.setParameter("false", false);
		typedQuery.setParameter("centerId", centerId);
		List<Test> listOfTests = typedQuery.getResultList();
		return listOfTests;
	}

	@Override
	public List<Appointment> getAppointmentList(BigInteger userId) {
		User user = entityManager.find(User.class, userId);
		TypedQuery<Appointment> typedQuery = entityManager.createQuery("FROM Appointment WHERE user = :userObject", Appointment.class);
		typedQuery.setParameter("userObject", user);
		List<Appointment> userAppointmentList = typedQuery.getResultList();
		return userAppointmentList;
	}

	@Override
	public boolean approveAppointment(BigInteger appointmentId) {
		Appointment appointment = entityManager.find(Appointment.class, appointmentId);
		appointment.setAppointmentstatus(1);
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
		TypedQuery<Appointment> typedQuery = entityManager.createQuery("FROM Appointment WHERE center = :ID AND appointmentStatus = :status",Appointment.class);
		typedQuery.setParameter("ID", center);
		typedQuery.setParameter("status", 0);
		List<Appointment> appointmentList = typedQuery.getResultList();
		return appointmentList;
	}

}