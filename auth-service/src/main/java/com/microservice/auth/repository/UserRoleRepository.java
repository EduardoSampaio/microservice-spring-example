package com.microservice.auth.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.auth.domain.UserRole;
import com.microservice.auth.domain.UserRoleId;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
	
	@Query("select ur from UserRole ur where ur.userid = :id")
	List<UserRole> findAllRoles(UUID id);
}
