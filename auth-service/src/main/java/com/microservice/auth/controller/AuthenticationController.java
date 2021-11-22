package com.microservice.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.auth.dto.CurrentUserDTO;
import com.microservice.auth.dto.JwtAuthenticationDTO;
import com.microservice.auth.service.AuthenticationService;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	

	@PostMapping("login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationDTO auth) {
		CurrentUserDTO createAuthentication = authenticationService.createAuthentication(auth);
		return ResponseEntity.ok(createAuthentication);
	}
	
	@GetMapping("refresh")
	public  ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {	
		String refreshedToken = authenticationService.refreshToken(request);
		if(refreshedToken != null) {
			return ResponseEntity.ok(refreshedToken);
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("change-password")
	public void changePassword() {}
	
	@PostMapping("rememberme")
	public void rememberPassword() {}
	
	@PutMapping("enable-twofactory")
	public void enableTwoFactoryAuthentication() {}
	
	@GetMapping("locked")
	public void lockedUser() {}
	
	@GetMapping("active")
	public void activeUser() {}
	
	public void updateUser() {}
}
