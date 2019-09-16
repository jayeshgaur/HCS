package com.cg.healthcaresystem.dto;

import java.math.BigInteger;

public class DiagnosticCenter {
	private BigInteger centerId;
//	private static Integer centerCounter=0;
//	private Integer testCounter=0;
//	private Integer appointmentCounter=0;
	//private static final String prefix = "HSC";
	private String centerName;
	private BigInteger centerContactNo;
	private String centerAddress;	
	//private List<Test> listOfTests = new ArrayList<Test>();
	//private List<Appointment> listOfAppointments = new ArrayList<Appointment>();
	
	public DiagnosticCenter()
	{
		
	}
	public DiagnosticCenter(String centerName, String centerAddress ,BigInteger centerContactNo) {
		super();
		this.centerName = centerName;
		this.centerContactNo = centerContactNo;
		this.centerAddress = centerAddress;
	}
	public BigInteger getCenterId() {
		return centerId;
	}
	public void setCenterId(BigInteger centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public BigInteger getCenterContactNo() {
		return centerContactNo;
	}
	public void setCenterContactNo(BigInteger centerContactNo) {
		this.centerContactNo = centerContactNo;
	}
	public String getCenterAddress() {
		return centerAddress;
	}
	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centerAddress == null) ? 0 : centerAddress.hashCode());
		result = prime * result + ((centerContactNo == null) ? 0 : centerContactNo.hashCode());
		result = prime * result + ((centerId == null) ? 0 : centerId.hashCode());
		result = prime * result + ((centerName == null) ? 0 : centerName.hashCode());
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
		DiagnosticCenter other = (DiagnosticCenter) obj;
		if (centerAddress == null) {
			if (other.centerAddress != null)
				return false;
		} else if (!centerAddress.equals(other.centerAddress))
			return false;
		if (centerContactNo == null) {
			if (other.centerContactNo != null)
				return false;
		} else if (!centerContactNo.equals(other.centerContactNo))
			return false;
		if (centerId == null) {
			if (other.centerId != null)
				return false;
		} else if (!centerId.equals(other.centerId))
			return false;
		if (centerName == null) {
			if (other.centerName != null)
				return false;
		} else if (!centerName.equals(other.centerName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", centerContactNo="
				+ centerContactNo + ", centerAddress=" + centerAddress + "]";
	}
	
	
	
}