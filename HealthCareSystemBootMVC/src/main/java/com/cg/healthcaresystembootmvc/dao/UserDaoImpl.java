package com.cg.healthcaresystembootmvc.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;
import com.cg.healthcaresystembootmvc.dto.Test;
import com.cg.healthcaresystembootmvc.dto.User;


@Repository
public class UserDaoImpl implements UserDao {
 
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public DiagnosticCenter addCenter(DiagnosticCenter center) {
		//Persist the new center
		entityManager.persist(center);
		return center;
	}

	@Override
	public boolean removeCenter(BigInteger centerId) {
		//get the object corresponding to the center Id
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		
		//mark the object as deleted by setting Deleted to true
		center.setDeleted(true);
		return true;
	}

	@Override
	public Test addTest(BigInteger centerId, Test test) {
		//get the object corresponding to the center Id
		DiagnosticCenter center = entityManager.find(DiagnosticCenter.class, centerId);
		
		//add the test into center's testList before persisting
		center.getListOfTests().add(test);
		
		//persist the test object
		entityManager.persist(test);
		return test;
	}

	@Override
	public boolean removeTest(BigInteger removeCenterId, BigInteger removeTestId){
		//get the object corresponding to the testId
		Test test = entityManager.find(Test.class, removeTestId);
		
		//mark as deleted
		test.setDeleted(true);
		return true;
	}

	@Override
	public Appointment addAppointment(Appointment appointment) {
		
		//persist the appointmentId
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
		appointment.setAppointmentStatus(1);
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
	
	@Override
	public BigInteger getUserLogin(String email, String password) {
		Query query = entityManager.createQuery("FROM User WHERE user_email = :EMAIL AND user_password = :PASSWORD");
		query.setParameter("EMAIL", email);
		query.setParameter("PASSWORD", password);
		User user;
		try {
		user = (User) query.getSingleResult();
		}catch(NoResultException noResultException) {
			return null;
		}
		return user.getUserId();		
	}

}