package com.microservice.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.auth.dto.CurrentUserDTO;
import com.microservice.auth.dto.JwtAuthenticationDTO;
import com.microservice.auth.service.AuthenticationService;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	

	@PostMapping("api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationDTO auth) {
		CurrentUserDTO createAuthentication = authenticationService.createAuthentication(auth);
		return ResponseEntity.ok(createAuthentication);
	}
	
	@PostMapping("api/refresh")
	public  ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {	
		String refreshedToken = authenticationService.refreshToken(request);
		if(refreshedToken != null) {
			return ResponseEntity.ok(refreshedToken);
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
}
