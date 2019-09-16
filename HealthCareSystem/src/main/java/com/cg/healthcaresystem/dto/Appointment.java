package com.cg.healthcaresystem.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;


public class Appointment {

	private BigInteger appointmentid;
	private BigInteger centerid;
	private BigInteger testid;
	private BigInteger userid;
	private int appointmentstatus;
	private LocalDateTime dateTime;
	private int isEmpty;

	public Appointment() {

	}

	public Appointment( BigInteger centerid, BigInteger testid, BigInteger userid,LocalDateTime dateTime) {
		super();
		this.centerid = centerid;
		this.testid = testid;
		this.userid = userid;
		this.dateTime = dateTime;
	}

	public BigInteger getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(BigInteger appointmentid) {
		this.appointmentid = appointmentid;
	}

	public BigInteger getCenterid() {
		return centerid;
	}

	public void setCenterid(BigInteger centerid) {
		this.centerid = centerid;
	}

	public BigInteger getTestid() {
		return testid;
	}

	public void setTestid(BigInteger testid) {
		this.testid = testid;
	}

	public BigInteger getUserid() {
		return userid;
	}

	public void setUserid(BigInteger userid) {
		this.userid = userid;
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
		result = prime * result + ((appointmentid == null) ? 0 : appointmentid.hashCode());
		result = prime * result + appointmentstatus;
		result = prime * result + ((centerid == null) ? 0 : centerid.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + isEmpty;
		result = prime * result + ((testid == null) ? 0 : testid.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		if (appointmentid == null) {
			if (other.appointmentid != null)
				return false;
		} else if (!appointmentid.equals(other.appointmentid))
			return false;
		if (appointmentstatus != other.appointmentstatus)
			return false;
		if (centerid == null) {
			if (other.centerid != null)
				return false;
		} else if (!centerid.equals(other.centerid))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (isEmpty != other.isEmpty)
			return false;
		if (testid == null) {
			if (other.testid != null)
				return false;
		} else if (!testid.equals(other.testid))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentid=" + appointmentid + ", centerid=" + centerid + ", testid=" + testid
				+ ", userid=" + userid + ", appointmentstatus=" + appointmentstatus + ", dateTime=" + dateTime
				+ ", isEmpty=" + isEmpty + "]";
	}
	
	

}
