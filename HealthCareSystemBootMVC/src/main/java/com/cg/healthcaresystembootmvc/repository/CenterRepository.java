package com.cg.healthcaresystembootmvc.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;


public interface CenterRepository extends JpaRepository<DiagnosticCenter,BigInteger> {

	@Query("FROM DiagnosticCenter WHERE isDeleted = 'false'")
	public List<DiagnosticCenter> getCenterList();
}
