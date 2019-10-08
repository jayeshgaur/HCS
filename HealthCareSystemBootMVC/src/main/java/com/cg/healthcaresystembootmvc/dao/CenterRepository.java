package com.cg.healthcaresystembootmvc.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.healthcaresystembootmvc.dto.DiagnosticCenter;

@Repository
public interface CenterRepository extends JpaRepository<DiagnosticCenter,BigInteger> {

}
