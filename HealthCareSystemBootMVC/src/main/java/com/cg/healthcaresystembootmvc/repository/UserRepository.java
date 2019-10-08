package com.cg.healthcaresystembootmvc.repository;

/*
 * author: Jayesh Gaur
 */

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.healthcaresystembootmvc.dto.User;

public interface UserRepository extends JpaRepository<User, BigInteger>{

}
