package com.cg.healthcaresystem.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="hcs_appointment")
public class Appointment {

	@Id @GeneratedValue
	@Column(name="appointment_id")
	private BigInteger appointmentId;
	
	@ManyToOne
	@JoinColumn(name="center_id_fk")
	private DiagnosticCenter center;
	
	@ManyToOne
	@JoinColumn(name="test_id_fk")
	private Test test;
	
	@ManyToOne
	@JoinColumn(name="user_id_fk")
	private User user;
	
	@Column(name="appointment_status")
	private int appointmentstatus;
	
	@Column(name="appointment_date_time")
	private LocalDateTime dateTime;
	
	public Appointment() {

	}

	
}
