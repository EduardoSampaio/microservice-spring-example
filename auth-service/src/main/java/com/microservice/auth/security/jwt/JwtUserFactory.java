package com.microservice.auth.security.jwt;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.microservice.auth.domain.User;

public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}

	public static JwtUser create(User user) {
		return new JwtUser(user.getId().toString(), user.getUsername(), user.getPassword(), new ArrayList<GrantedAuthority>());
	}
}
