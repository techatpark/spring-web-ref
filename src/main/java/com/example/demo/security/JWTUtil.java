package com.example.demo.security;

import com.example.demo.constants.ApplicationConstants;
import com.example.demo.model.User;
import java.security.Key;
import java.security.MessageDigest;
import java.util.*;

import javax.annotation.PostConstruct;

import com.example.demo.util.UUIDGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@Component
public class JWTUtil {

	@Autowired
	private CacheManager cacheManager;
	
	@Value("${springbootwebfluxjjwt.jjwt.secret}")
	private String secret;
	
	@Value("${springbootwebfluxjjwt.jjwt.expiration}")
	private String expirationTime;

	private Key key;

	private Cache authCache;


	@PostConstruct
	public void init(){
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.authCache = cacheManager.getCache("Auth");
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public void logout(ServerWebExchange swe,String tenantCode) {
		authCache.evict(getDigest(swe)+ApplicationConstants.WORD_SEPARATOR+tenantCode);
	}

	public Mono<Authentication> getAuthentication(String digest) {
		Cache.ValueWrapper valueWrapper = authCache.get(digest);

		if(valueWrapper == null) {
			return Mono.empty();
		}
		String authToken = (String) valueWrapper.get();
			try {
				String username = getUsernameFromToken(authToken);
				if (!validateToken(authToken)) {
					return Mono.empty();
				}
				Claims claims = getAllClaimsFromToken(authToken);
				List<String> rolesMap = claims.get("role", List.class);
				List<GrantedAuthority> authorities = new ArrayList<>();
				for (String rolemap : rolesMap) {
					authorities.add(new SimpleGrantedAuthority(rolemap));
				}
				return Mono.just(new UsernamePasswordAuthenticationToken(username, null, authorities));
			} catch (Exception e) {
				return Mono.empty();
			}


	}

	public String getDigest(ServerWebExchange swe) {
		ServerHttpRequest request = swe.getRequest();
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
	
	public String getUsernameFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(User user,String tenentCode) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRoles());
		String jwtToken = doGenerateToken(claims, user.getUsername());

		String digest = UUID.randomUUID().toString();

		authCache.put(digest+ ApplicationConstants.WORD_SEPARATOR + tenentCode,jwtToken);
		return digest;
	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		Long expirationTimeLong = Long.parseLong(expirationTime); //in second
		
		final Date createdDate = new Date();
		final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(key)
				.compact();
	}
	
	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

}
