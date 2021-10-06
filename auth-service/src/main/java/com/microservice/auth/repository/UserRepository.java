package com.microservice.auth.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.auth.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
