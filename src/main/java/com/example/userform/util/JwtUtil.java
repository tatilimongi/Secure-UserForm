package com.example.userform.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "MySuperSecretKeyThatNeedsToBeVeryLongToBeSecure123!";
	private final long EXPIRATION_TIME = 1000 * 60 * 60;
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	public String generateToken(String email, String role) {
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenExpired(String token) {
		try {
			Date expirationDate = Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getExpiration();

			return expirationDate.before(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public String extractRole(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("role", String.class);
	}

	public String extractEmail(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public Authentication getAuthentication(String token) {
		String email = extractEmail(token);
		return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
	}
}
