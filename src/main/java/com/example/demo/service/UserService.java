package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.security.model.Role;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {


	private final PasswordEncoder passwordEncoder;
	
	// this is just an example, you can load the user from the database from the repository

	private Map<String, User> data;

	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void init(){
		data = new HashMap<>();
		
		//username:passwowrd -> user:user
		data.put("user", new User("user"
				, passwordEncoder.encode("user")
				, true, Arrays.asList(Role.ROLE_USER)));

		//username:passwowrd -> admin:admin
		data.put("admin", new User("admin"
				, passwordEncoder.encode("user")
				, true, Arrays.asList(Role.ROLE_ADMIN)));
	}
	
	public Mono<User> findByUsername(String username) {
		if (data.containsKey(username)) {
			return Mono.just(data.get(username));
		} else {
			return Mono.empty();
		}
	}
	
}
