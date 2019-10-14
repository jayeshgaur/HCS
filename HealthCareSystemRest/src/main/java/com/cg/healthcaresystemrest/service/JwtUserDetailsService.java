package com.cg.healthcaresystemrest.service;

import java.util.ArrayList;

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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("javainuse".equals(username)) {
			return new org.springframework.security.core.userdetails.User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	public User save(UserDetailsImpl user) {
		User newUser = new User();
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUserPassword(brcyptEncoder.encode(user.getUserPassword()));
		return userRepository.save(newUser);
	}
	
}