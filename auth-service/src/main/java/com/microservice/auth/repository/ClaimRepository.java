package com.microservice.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.auth.domain.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
