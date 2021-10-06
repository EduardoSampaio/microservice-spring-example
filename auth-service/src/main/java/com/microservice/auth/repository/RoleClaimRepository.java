package com.microservice.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.auth.domain.RoleClaim;
import com.microservice.auth.domain.RoleClaimId;

@Repository
public interface RoleClaimRepository extends JpaRepository<RoleClaim, RoleClaimId>{

}
