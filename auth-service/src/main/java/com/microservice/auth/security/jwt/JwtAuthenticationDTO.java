package com.microservice.auth.security.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

}
