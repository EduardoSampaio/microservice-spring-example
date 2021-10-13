package com.microservice.auth.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class UserRoleId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "roleId")
    private Long roleid;

    @Column(name = "userId")
    private UUID userId;

}
