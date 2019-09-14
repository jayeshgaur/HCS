package com.cg.healthcaresystem.dto;

import java.time.LocalDateTime;


public class Appointment {

	private final String prefix = "App";
	private User user;
	private String appointmentId;
	private Test test;
	private DiagnosticCenter center;
	private boolean approved;
	private LocalDateTime dateTime;

	public Appointment() {

	}

	public Appointment(User user, Test test, DiagnosticCenter center, LocalDateTime dateTime) {
		super();
		this.setAppointmentId(prefix + center.getAppointmentCounter().toString());
		this.setApproved(false);
		this.user = user;
		this.test = test;
		this.center = center;
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public DiagnosticCenter getCenter() {
		return center;
	}

	public void setCenter(DiagnosticCenter center) {
		this.center = center;
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean setApproved(boolean approved) {
		return (this.approved = approved);
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}


}
