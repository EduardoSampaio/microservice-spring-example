package com.microservice.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.auth.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
