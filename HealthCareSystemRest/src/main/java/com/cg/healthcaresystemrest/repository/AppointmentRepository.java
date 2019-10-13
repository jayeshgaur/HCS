package com.cg.healthcaresystemrest.repository;

/*
 * Author:			 Jayesh Gaur
 * Description:		 Repository class to manage the entity Appointment
 * Created on:		 October 9, 2019
 */

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cg.healthcaresystemrest.dto.Appointment;
import com.cg.healthcaresystemrest.dto.DiagnosticCenter;
import com.cg.healthcaresystemrest.dto.User;



public interface AppointmentRepository extends JpaRepository<Appointment, BigInteger>{
	//@Query("FROM Appointment WHERE center = :center AND	 appointment_status = :status")
	public List<Appointment> findByCenterAndAppointmentStatus(@Param("center")DiagnosticCenter center,@Param("status") int status);

//	@Query("FROM Appointment WHERE user = :userObject")
	public List<Appointment> findByUser(@Param("userObject")User user);
}
