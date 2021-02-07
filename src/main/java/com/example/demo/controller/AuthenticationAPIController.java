package com.example.demo.controller;

import com.example.demo.constants.ApplicationConstants;
import com.example.demo.model.Tenant;
import com.example.demo.security.JWTUtil;
import com.example.demo.security.PBKDF2Encoder;
import com.example.demo.security.model.AuthRequest;
import com.example.demo.security.model.AuthResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/{tenantCode}")
public class AuthenticationAPIController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(final Tenant tenant,
										 @RequestBody final AuthRequest ar) {
		return userService.findByUsername(ar.getUsername()
				+ ApplicationConstants.WORD_SEPARATOR + tenant.getCode())
				.map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails, tenant.getCode())));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@RequestMapping(value = "/api/logout", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> logout(final Tenant tenant
			,final ServerWebExchange exchange) {
		jwtUtil.logout(exchange,tenant.getCode());
		return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
	}


}
