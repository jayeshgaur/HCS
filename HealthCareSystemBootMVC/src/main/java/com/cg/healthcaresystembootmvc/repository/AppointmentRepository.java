package com.cg.healthcaresystembootmvc.repository;

/*
 * Author:			 Jayesh Gaur
 * Description:		 Repository class to manage the entity Appointment
 * Created on:		 October 9, 2019
 */

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.healthcaresystembootmvc.dto.Appointment;
import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;

public interface AppointmentRepository extends JpaRepository<Appointment, BigInteger>{
	@Query("FROM Appointment WHERE appointmentStatus = 0")
	public List<Appointment> findByCenter(DiagnosticCenter center);
}
