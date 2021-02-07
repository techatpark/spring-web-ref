package com.example.demo.service;

import com.example.demo.model.Tenant;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TenantService {

	private List<Tenant> tenants;

	@PostConstruct
	public void init(){
		tenants = new ArrayList<>();

		Tenant tenant = new Tenant();
		tenant.setCode("tenant1");
		tenant.setName("Tenant One");
		tenants.add(tenant);

		tenant = new Tenant();
		tenant.setCode("tenant2");
		tenant.setName("Tenant Two");
		tenants.add(tenant);

	}
	
	public Mono<Tenant> findByCode(String code) {
		Optional<Tenant> tenant = tenants.stream().filter(t->t.getCode().equals(code)).findFirst();
		if (tenant.isPresent()) {
			return Mono.just(tenant.get());
		} else {
			return Mono.empty();
		}
	}

	public String getTenantCode(ServerWebExchange serverWebExchange) {
		String path = serverWebExchange.getRequest().getPath().value();
		int secondIndexofSlash = path.indexOf("/",2);
		return (secondIndexofSlash == -1) ? path.substring(1) : path.substring(1,path.indexOf("/",2));
	}
	
}
