package com.cg.healthcaresystembootmvc.repository;

import java.math.BigInteger;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthcaresystembootmvc.dto.Test;

/*
 * Author : Nidhi
 * Description : This inteface will perform CRUD operations for Test Entity.
 * Created Date : 9th October,2019 
 * 
 * */
public interface TestRepository extends JpaRepository<Test,BigInteger> {

	
	
	 

}
