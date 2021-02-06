package com.example.demo.rest;

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

/**
 *
 * @author ard333
 */
@RestController
public class AuthenticationREST {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		return userService.findByUsername(ar.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@RequestMapping(value = "/api/logout", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> logout(ServerWebExchange exchange) {
		jwtUtil.logout(exchange);
		return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
	}


}
