package com.cg.healthcaresystem.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hcs_center")
public class DiagnosticCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "center_id")
	private BigInteger centerId;

	@Column(name = "center_name")
	private String centerName;

	@Column(name = "center_contact_no")
	private BigInteger centerContactNo;

	@Column(name = "center_address")
	private String centerAddress;
	
	@OneToMany
	@JoinColumn(name="center_id_fk")
	private List<Test> listOfTests = new ArrayList<Test>();
	
	@OneToMany(mappedBy = "center")
	List<Appointment> userAppointmentList = new ArrayList<Appointment>();

	public DiagnosticCenter() {
	}

	public DiagnosticCenter(String centerName, String centerAddress, BigInteger centerContactNo) {
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

	public List<Test> getListOfTests() {
		return listOfTests;
	}

	public void setListOfTests(List<Test> listOfTests) {
		this.listOfTests = listOfTests;
	}

}