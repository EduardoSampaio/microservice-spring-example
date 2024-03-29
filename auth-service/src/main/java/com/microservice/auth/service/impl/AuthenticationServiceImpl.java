package com.microservice.auth.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.microservice.auth.domain.User;
import com.microservice.auth.dto.CurrentUserDTO;
import com.microservice.auth.dto.JwtAuthenticationDTO;
import com.microservice.auth.repository.UserRepository;
import com.microservice.auth.security.jwt.JwtTokenUtil;
import com.microservice.auth.security.jwt.JwtUser;
import com.microservice.auth.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public CurrentUserDTO createAuthentication(JwtAuthenticationDTO auth) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()
		));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		final String token = "Bearer " + jwtTokenUtil.generateToken(jwtUser);
		
		return CurrentUserDTO.builder()
				.username(jwtUser.getUsername())
				.token(token)
				.authorities(jwtUser.getAuthorities())
				.build();
	}

	@Override
	public String refreshToken(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").split(" ")[1];
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		if(userOpt.isPresent() && jwtTokenUtil.canTokenBeRefreshed(authToken)) {
			return jwtTokenUtil.refreshToken(authToken);
		}
		return null;
	}

}
