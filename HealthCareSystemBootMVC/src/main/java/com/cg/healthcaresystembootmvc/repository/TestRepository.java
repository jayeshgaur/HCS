package com.cg.healthcaresystembootmvc.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.healthcaresystembootmvc.dto.Test;

//Test Repository
public interface TestRepository extends JpaRepository<Test,BigInteger> {

	
	
	 

}
