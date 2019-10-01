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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="hcs_appointment")
public class Appointment {

	@Id @GeneratedValue
	@Column(name="appointment_id")
	private BigInteger appointmentId;
	
	public Appointment(BigInteger appointmentId, DiagnosticCenter center, Test test, User user, int appointmentStatus,
			LocalDateTime dateTime) {
		super();
		this.appointmentId = appointmentId;
		this.center = center;
		this.test = test;
		this.user = user;
		this.appointmentStatus = appointmentStatus;
		this.dateTime = dateTime;
	}

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
	private int appointmentStatus;
	
	//@DateTimeFormat(pattern="MM/dd/yyyy hh:mm a")
	@Column(name="appointment_date_time")
	private LocalDateTime dateTime;
	
	public Appointment() {

	}
	
	

	public DiagnosticCenter getCenter() {
		return center;
	}

	public void setCenter(DiagnosticCenter center) {
		this.center = center;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAppointmentstatus() {
		return appointmentStatus;
	}

	public void setAppointmentstatus(int appointmentstatus) {
		this.appointmentStatus = appointmentstatus;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public BigInteger getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(BigInteger appointmentId) {
		this.appointmentId = appointmentId;
	}

	
}
