package com.microservice.auth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class RoleClaimId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "roleId")
    private Long roleid;

    @Column(name = "claimId")
    private Long claimId;
}
