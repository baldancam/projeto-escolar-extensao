package com.sistema.escolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.security.JwtTokenProvider;
import com.sistema.escolar.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String token = jwtTokenProvider.generateToken(userDetails.getUsername());

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}
}

class LoginRequest {
	private String username;
	private String password;

	// Getters e Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

class JwtAuthenticationResponse {
	private String accessToken;

	public JwtAuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	// Getters
	public String getAccessToken() {
		return accessToken;
	}

	// Setters (opcional)
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
