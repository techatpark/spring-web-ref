package com.example.demo.service;

import com.example.demo.constants.ApplicationConstants;
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

	private Map<String, Map<String, User>> data;

	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void init(){

		data = new HashMap<>();

		Map<String, User> users = new HashMap<>();

		//username:passwowrd -> user:user
		users.put("user", new User("user"
				, passwordEncoder.encode("tenant1")
				, true, Arrays.asList(Role.ROLE_USER)));

		//username:passwowrd -> admin:admin
		users.put("admin", new User("admin"
				, passwordEncoder.encode("tenant1")
				, true, Arrays.asList(Role.ROLE_ADMIN)));

		data.put("tenant1",users);

		users = new HashMap<>();

		//username:passwowrd -> user:user
		users.put("user", new User("user"
				, passwordEncoder.encode("tenant2")
				, true, Arrays.asList(Role.ROLE_USER)));

		//username:passwowrd -> admin:admin
		users.put("admin", new User("admin"
				, passwordEncoder.encode("tenant2")
				, true, Arrays.asList(Role.ROLE_ADMIN)));

		data.put("tenant2",users);
	}
	
	public Mono<User> findByUsername(String userNameWithTenantCode) {
		int splitPoint = userNameWithTenantCode.indexOf(ApplicationConstants.WORD_SEPARATOR);
		String tenantCode = userNameWithTenantCode
				.substring(splitPoint+1);
		String userName = userNameWithTenantCode.substring(0,splitPoint);
		User user = null ;
		if (data.containsKey(tenantCode) && (user=data.get(tenantCode).get(userName)) != null) {
			return Mono.just(user);
		} else {
			return Mono.empty();
		}
	}
	
}
