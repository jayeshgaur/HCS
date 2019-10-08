package com.cg.healthcaresystembootmvc.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.healthcaresystembootmvc.dto.User;

/*
 * author: Jayesh Gaur
 */

//User Repository
public interface UserRepository extends JpaRepository<User, BigInteger>{

}
