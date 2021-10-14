package com.microservice.auth.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.auth.domain.User;
import com.microservice.auth.repository.UserRepository;
import com.microservice.auth.security.jwt.JwtUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.",username));
		}
		

		return JwtUser.builder()
				.id(userOpt.get().getId().toString())
				.username(userOpt.get().getUsername())
				.password(userOpt.get().getPassword())
				.authorities(null)
				.build();
	}
	
	

}
