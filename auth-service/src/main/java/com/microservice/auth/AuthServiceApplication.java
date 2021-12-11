package com.microservice.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservice.auth.domain.User;
import com.microservice.auth.repository.UserRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthServiceApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	private void initRootUser() {
		User admin = new User();
		admin.setUsername("admin");
		admin.setEmail("admin@email.com");
		admin.setPassword(encoder.encode("1234"));
		admin.setCreatedAt(LocalDateTime.now());
		admin.setUpdatedAt(LocalDateTime.now());

		Optional<User> userOpt = userRepository.findByUsername("admin");
		if (userOpt.isEmpty()) {
			userRepository.save(admin);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		initRootUser();
	}
}
