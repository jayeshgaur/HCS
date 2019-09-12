package com.cg.healthcaresystem.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticCenter {
	private String centerId;
	private static Integer centerCounter=0;
//	private Integer testCounter=0;
	private Integer appointmentCounter=0;
	private static final String prefix = "HSC";
	private String centerName;
	private BigInteger centerContactNo;
	private String centerAddress;	
	private List<Test> listOfTests = new ArrayList<Test>();
	private List<Appointment> listOfAppointments = new ArrayList<Appointment>();
	
	public DiagnosticCenter()
	{
		
	}
	
	//Constructor
	public DiagnosticCenter(String centerName, String centerAddress, BigInteger centerNo) {
		super();
		this.setCenterName(centerName);
		this.setCenterId(prefix + (centerCounter++).toString());
		this.setCenterAddress(centerAddress);
		this.setCenterContactNo(centerNo);
		Test t1 = new Test("Blood Test");
		Test t2 = new Test("Blood Group");
		Test t3 = new Test("Blood Pressure");
		this.addTest(t1);
		this.addTest(t2);
		this.addTest(t3);
	}

	
	//Center ID
	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId=centerId;
	}
	
	//Center Name
	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		
		this.centerName = centerName ;
	}

	//ListOfTests
	public List<Test> getListOfTests() {
		return listOfTests;
	}
	
	public void setListOfTests(List<Test> listOfTests) {
		this.listOfTests =  listOfTests;
	}

	public void addTest(Test t) {
		this.listOfTests.add(t);
	}

	//CenterContactNumber
	public BigInteger getCenterContactNo() {
		return centerContactNo;
	}

	public void setCenterContactNo(BigInteger centerNo) {
		this.centerContactNo = centerNo;
	}

	//Center Address
	public String getCenterAddress() {
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}

	//ListOfAppointments
	public List<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}

	public void setListOfAppointments(List<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centerAddress == null) ? 0 : centerAddress.hashCode());
		result = prime * result + ((centerContactNo == null) ? 0 : centerContactNo.hashCode());
		result = prime * result + ((centerId == null) ? 0 : centerId.hashCode());
		result = prime * result + ((centerName == null) ? 0 : centerName.hashCode());
		result = prime * result + ((listOfAppointments == null) ? 0 : listOfAppointments.hashCode());
		result = prime * result + ((listOfTests == null) ? 0 : listOfTests.hashCode());
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
		if (listOfAppointments == null) {
			if (other.listOfAppointments != null)
				return false;
		} else if (!listOfAppointments.equals(other.listOfAppointments))
			return false;
		if (listOfTests == null) {
			if (other.listOfTests != null)
				return false;
		} else if (!listOfTests.equals(other.listOfTests))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", centerContactNo="
				+ centerContactNo + ", centerAddress=" + centerAddress + ", listOfTests=" + listOfTests
				+ ", listOfAppointments=" + listOfAppointments + "]";
	}

	public Integer getAppointmentCounter() 
	{
		appointmentCounter++;
		return appointmentCounter;
	}

	public void setAppointmentCounter(Integer appointmentCounter) 
	{
		this.appointmentCounter = appointmentCounter;
	}
	
	public boolean addAppointment(Appointment appointment)
	{
		return this.listOfAppointments.add(appointment);
	}

}
