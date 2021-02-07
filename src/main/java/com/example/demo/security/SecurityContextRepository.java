package com.example.demo.security;

import com.example.demo.constants.ApplicationConstants;
import com.example.demo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository{
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
		throw new UnsupportedOperationException("sSS");
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange swe) {
		String authToken = jwtUtil.getDigest(swe);

		if (authToken != null ) {
			Authentication auth = new UsernamePasswordAuthenticationToken(authToken,authToken+ ApplicationConstants.WORD_SEPARATOR + tenantService.getTenantCode(swe));
			return this.authenticationManager.authenticate(auth).map((authentication) -> {
				return new SecurityContextImpl(authentication);
			});
		} else {
			return Mono.empty();
		}
	}


	
}
