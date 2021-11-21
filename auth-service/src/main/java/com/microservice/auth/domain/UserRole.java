package com.microservice.auth.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users_roles")
@Getter 
@Setter

public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private UserRoleId id;
	
	@Column(name = "roleid", insertable = false, updatable = false)
    private Long roleId;

    @Column(name = "userid", insertable = false, updatable = false)
    private UUID userid;

    @ManyToOne
    @MapsId("roleid")
    @JoinColumn(name = "roleid")
    Role role;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "userid")
    User user;
}
