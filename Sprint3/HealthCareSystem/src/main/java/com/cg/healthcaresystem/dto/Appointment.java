package com.cg.healthcaresystem.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;


public class Appointment {

	private BigInteger appointmentId;
	private BigInteger centerId;
	private BigInteger testId;
	private BigInteger userId;
	private int appointmentstatus;
	private LocalDateTime dateTime;
	private int isEmpty;

	public Appointment() {

	}

	public Appointment( BigInteger centerid, BigInteger testid, BigInteger userid,LocalDateTime dateTime) {
		super();
		this.centerId = centerid;
		this.testId = testid;
		this.userId = userid;
		this.dateTime = dateTime;
	}

	public BigInteger getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(BigInteger appointmentid) {
		this.appointmentId = appointmentid;
	}

	public BigInteger getCenterid() {
		return centerId;
	}

	public void setCenterId(BigInteger centerid) {
		this.centerId = centerid;
	}

	public BigInteger getTestId() {
		return testId;
	}

	public void setTestid(BigInteger testId) {
		this.testId = testId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userid) {
		this.userId = userid;
	}

	public int getAppointmentstatus() {
		return appointmentstatus;
	}

	public void setAppointmentstatus(int appointmentstatus) {
		this.appointmentstatus = appointmentstatus;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(int isEmpty) {
		this.isEmpty = isEmpty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointmentId == null) ? 0 : appointmentId.hashCode());
		result = prime * result + appointmentstatus;
		result = prime * result + ((centerId == null) ? 0 : centerId.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + isEmpty;
		result = prime * result + ((testId == null) ? 0 : testId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentId == null) {
			if (other.appointmentId != null)
				return false;
		} else if (!appointmentId.equals(other.appointmentId))
			return false;
		if (appointmentstatus != other.appointmentstatus)
			return false;
		if (centerId == null) {
			if (other.centerId != null)
				return false;
		} else if (!centerId.equals(other.centerId))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (isEmpty != other.isEmpty)
			return false;
		if (testId == null) {
			if (other.testId != null)
				return false;
		} else if (!testId.equals(other.testId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentid=" + appointmentId + ", centerid=" + centerId + ", testid=" + testId
				+ ", userid=" + userId + ", appointmentstatus=" + appointmentstatus + ", dateTime=" + dateTime
				+ ", isEmpty=" + isEmpty + "]";
	}
	
	

}
