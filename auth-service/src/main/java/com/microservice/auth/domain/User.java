package com.microservice.auth.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "username", length = 30, nullable = false)
	private String username;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "phonenumber", length = 30, nullable = true)
	private String phoneNumber;

	@Column(name = "tokenaccess", length = 30, nullable = true)
	private String tokenAccess;

	@Column(name = "deviceaccess", length = 50, nullable = true)
	private String deviceAccess;

	@Column(name = "lastaccess", columnDefinition = "TIMESTAMP")
	private LocalDateTime lastAccess;

	@Column(name = "createdat", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "updatedat", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	@Column(name = "twofactoryauthentication", nullable = true)
	private boolean twoFactoryAuthentication;

	@Column(name = "isactive", nullable = true)
	private boolean isActive;

	@Column(name = "islocked", nullable = true)
	private boolean isLocked;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	private Set<UserRole> userRoles;
}