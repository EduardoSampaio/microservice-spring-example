package com.microservice.auth.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.auth.domain.User;
import com.microservice.auth.repository.UserRepository;
import com.microservice.auth.security.jwt.CurrentUserDTO;
import com.microservice.auth.security.jwt.JwtAuthenticationDTO;
import com.microservice.auth.security.jwt.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	

	@PostMapping("api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationDTO auth) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()
		));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
		final String token = "Bearer " + jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new CurrentUserDTO(userDetails.getUsername(),token));
	}
	
	@PostMapping("api/refresh")
	public  ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		if(userOpt.isPresent() && jwtTokenUtil.canTokenBeRefreshed(authToken)) {
			String refreshedToken = jwtTokenUtil.refreshToken(authToken);
			return ResponseEntity.ok(refreshedToken);
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
}
