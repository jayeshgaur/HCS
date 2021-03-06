package com.cg.healthcaresystembootmvc.repository;

/*
 * author: Jayesh Gaur
 */

import java.math.BigInteger;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cg.healthcaresystembootmvc.dto.User;

/*
 * Author: Jayesh Gaur
 * Description: User Repository class which handles all database related interactions for the DTO class User.
 * Created on: October 9, 2019
 */
public interface UserRepository extends JpaRepository<User, BigInteger> {
	
	public User findByUserEmailAndUserPassword(@Param("email")String email, @Param("password")String password);

	public User findByUserEmail(String userEmail);
	
	public User findByContactNo(BigInteger contactNo);
	
}
