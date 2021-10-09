package com.microservice.auth.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "username", length = 30, nullable = false)
	@Setter
	private String username;

	@Column(name = "email", length = 30, nullable = false)
	@Setter
	private String email;

	@Column(name = "password", length = 30, nullable = false)
	@Setter
	private String password;

	@Column(name = "phonenumber", length = 30, nullable = true)
	@Setter
	private String phoneNumber;

	@Column(name = "tokenaccess", length = 30, nullable = true)
	@Setter
	private String tokenAccess;

	@Column(name = "deviceaccess", length = 50, nullable = true)
	@Setter
	private String deviceAccess;

	@Column(name = "lastaccess", columnDefinition = "TIMESTAMP")
	@Setter
	private LocalDateTime lastAccess;

	@Column(name = "createdat", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "updatedat", columnDefinition = "TIMESTAMP")
	@Setter
	private LocalDateTime updatedAt;

	@Column(name = "twofactoryauthentication", nullable = true)
	@Setter
	private boolean twoFactoryAuthentication;

	@Column(name = "isactive", nullable = true)
	@Setter
	private boolean isActive;

	@Column(name = "islocked", nullable = true)
	@Setter
	private boolean isLocked;
}