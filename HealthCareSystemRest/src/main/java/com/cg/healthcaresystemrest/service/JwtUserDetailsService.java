package com.cg.healthcaresystemrest.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.healthcaresystemrest.dto.User;
import com.cg.healthcaresystemrest.dto.UserDetailsImpl;
import com.cg.healthcaresystemrest.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder brcyptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserEmail(userEmail);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with email: " + userEmail);
		}
		return user.map(UserDetailsImpl::new).get();
	}
	
	public User save(User user) {
		User newUser = new User(brcyptEncoder.encode(user.getUserPassword()), user.getUserName(), user.getContactNo(), user.getUserEmail(), user.getAge(), user.getGender());
		return userRepository.save(newUser);
	}
	
}