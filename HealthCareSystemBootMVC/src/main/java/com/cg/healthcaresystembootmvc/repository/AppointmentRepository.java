package com.cg.healthcaresystembootmvc.repository;

/*
 * Author:			 Jayesh Gaur
 * Description:		 Repository class to manage the entity Appointment
 * Created on:		 October 9, 2019
 */

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.healthcaresystembootmvc.dto.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, BigInteger>{

}
