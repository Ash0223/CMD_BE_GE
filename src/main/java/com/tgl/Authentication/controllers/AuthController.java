package com.tgl.Authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgl.Authentication.models.JwtUtil;
import com.tgl.Authentication.models.User;
import com.tgl.Authentication.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return authService.registerUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("---------------------------------------------" + user.getUsername() + "--------" + user.getPassword());
		String token = authService.login(user.getUsername(), user.getPassword());
		if(token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(401).body("Invalid Credentials");
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Boolean> validate(@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok(jwtUtil.validateToken(token.substring(7)));
	}

}
