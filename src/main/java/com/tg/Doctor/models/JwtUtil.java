//package com.tg.Doctor.models;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.security.Key;
//
//import io.jsonwebtoken.*;
//
//@Component
//public class JwtUtil {
//	// Secure key generation for HS256
//    private static final String secretKey = "mySuperSecretKeyForJWTAuthentication123";
//    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
//    
//	private final long EXPIRATION_TIME = 1000 * 60 * 60;
//	
////	public String generateToken(String username) {
////        return Jwts.builder()
////                .setSubject(username)
////                .setIssuedAt(new Date()) // Token creation time
////                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
////                .signWith(secretKey)
////                .compact();
////    }
//	
//	public String extractUsername(String token) { 
//	    return Jwts.parser()
//	            .setSigningKey(secretKey) // Convert key to bytes
//	            .build()
//	            .parseClaimsJws(token)
//	            .getBody()
//	            .getSubject();    
//	}
//	
//	
////	private boolean isTokenExpired(String token) {
////        return Jwts.parser()
////                .setSigningKey(secretKey)
////                .build()
////                .parseClaimsJws(token)
////                .getBody()
////                .getExpiration()
////                .before(new Date());
////    }
//	
//	 
////	// Extract the token from the Authorization header
////	    public String getJwtFromRequest(HttpServletRequest request) {
////	        String header = request.getHeader("Authorization");
////	        if (header != null && header.startsWith("Bearer ")) {
////	            return header.substring(7);  // Extract token after "Bearer "
////	        }
////	        return null;  // Return null if no token found
////	    }
//
//	    // Validate the token
//	    public boolean validateToken(String token) {
//	        try {
//	            Jwts.parser()
//	                .setSigningKey(secretKey)
//	                .build()
//	                .parseClaimsJws(token);  // Parse the token and check validity
//	            return true;  // Token is valid
//	        } catch (Exception e) {
//	            return false;  // Token is invalid
//	        }
//	    }
//
////	    // Extract Authentication object from token
////	    public Authentication getAuthentication(String token) {
////	        Claims claims = Jwts.parser()
////	            .setSigningKey(secretKey)
////	            .build()
////	            .parseClaimsJws(token)
////	            .getBody();
////	        
////	        String username = claims.getSubject();
////	        // You can also extract roles, permissions, etc. here
////
////	        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());  // Create an Authentication object (you can also include roles here)
////	    }
//} 
