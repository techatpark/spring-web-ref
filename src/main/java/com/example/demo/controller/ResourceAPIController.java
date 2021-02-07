package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.model.Tenant;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/{tenantCode}/api/resource")
public class ResourceAPIController {
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public Mono<ResponseEntity<?>> user(final Tenant tenant) {
		return Mono.just(ResponseEntity.ok(new Message("Content for user "+tenant.getCode())));
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> admin(final Tenant tenant) {
		return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
	}
	
	@RequestMapping(value = "/user-or-admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> userOrAdmin(final Tenant tenant) {
		return Mono.just(ResponseEntity.ok(new Message("Content for user or admin")));
	}
}
