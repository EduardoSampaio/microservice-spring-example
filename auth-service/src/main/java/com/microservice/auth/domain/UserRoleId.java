package com.microservice.auth.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "roleid")
    private Long roleId;

    @Column(name = "userid")
    private UUID userId;

}
