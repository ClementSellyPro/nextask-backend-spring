package com.nextask.nextask_app.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextask.nextask_app.api.DTO.LoginRequest;
import com.nextask.nextask_app.api.DTO.LoginResponse;
import com.nextask.nextask_app.api.Util.JwtUtil;
import com.nextask.nextask_app.api.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody LoginRequest request) {
		try {
			userService.createUser(request.getUsername(), request.getPassword());
			return ResponseEntity.ok("User created successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
			);
			String token = jwtUtil.generateToken(request.getUsername());

			return ResponseEntity.ok(new LoginResponse(token));
		} catch (Exception e) {
			System.err.println("Login failed: " + e.getMessage());
			return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials"));
		}
	}
}