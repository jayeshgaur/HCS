package com.cg.healthcaresystembootmvc.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthcaresystembootmvc.dto.Test;

//Test Repository
public interface TestRepository extends JpaRepository<Test,BigInteger> {

	
	
	 

}
