package com.microservice.auth.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles_claims")
@Getter 
@Setter
public class RoleClaim implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @EmbeddedId
    private RoleClaimId id;

    @ManyToOne
    @MapsId("roleid")
    @JoinColumn(name = "roleid")
    Role role;

    @ManyToOne
    @MapsId("userid")
    @JoinColumn(name = "claimId")
    Claim claim;
}
