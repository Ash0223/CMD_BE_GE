package com.tgl.Authentication.models;

import java.security.*;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET_KEY = "mySuperSecretKeyForJWTAuthentication123";
	private static final long EXPIRATION_TIME = 1000*60*60;
	
	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}
	//thus
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);	
			return true;
		} catch(JwtException e) {
			return false;
		}
	}
	
	public String extractUsername(String token) {
		return Jwts.parser()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody().
				getSubject();
	}
}
