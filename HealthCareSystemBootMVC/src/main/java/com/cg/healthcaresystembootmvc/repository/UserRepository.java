package com.cg.healthcaresystembootmvc.repository;

/*
 * author: Jayesh Gaur
 */

import java.math.BigInteger;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cg.healthcaresystembootmvc.dto.User;

/*
 * Author: Jayesh Gaur
 * Description: User Repository class which handles all database related interactions for the DTO class User.
 * Created on: October 9, 2019
 */
public interface UserRepository extends JpaRepository<User, BigInteger> {
	
	@Query("FROM User WHERE userEmail = :email AND userPassword = :password")
	public User findByUserEmailAndUserPassword(@Param("email")String email, @Param("password")String password);

}
