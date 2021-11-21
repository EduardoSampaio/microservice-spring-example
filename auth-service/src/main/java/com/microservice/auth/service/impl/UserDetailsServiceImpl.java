package com.microservice.auth.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.auth.domain.User;
import com.microservice.auth.domain.UserRole;
import com.microservice.auth.repository.UserRepository;
import com.microservice.auth.repository.UserRoleRepository;
import com.microservice.auth.security.jwt.JwtUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.",username));
		}
		
		List<UserRole> findAllRolesByUserId = userRoleRepository.findAllRoles(userOpt.get().getId());

		return JwtUser.builder()
				.id(userOpt.get().getId().toString())
				.username(userOpt.get().getUsername())
				.password(userOpt.get().getPassword())
				.authorities(null)
				.build();
	}
	
	

}
