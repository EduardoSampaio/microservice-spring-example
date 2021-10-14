package com.microservice.auth.service;

import javax.servlet.http.HttpServletRequest;

import com.microservice.auth.dto.CurrentUserDTO;
import com.microservice.auth.dto.JwtAuthenticationDTO;

public interface AuthenticationService {

	CurrentUserDTO createAuthentication(JwtAuthenticationDTO auth);
	
	String  refreshToken(HttpServletRequest request);
}
