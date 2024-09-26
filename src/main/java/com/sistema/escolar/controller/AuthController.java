package com.sistema.escolar.controller;

import com.sistema.escolar.request.LoginRequest;
import com.sistema.escolar.response.JwtResponse;
import com.sistema.escolar.security.JwtTokenProvider;
import com.sistema.escolar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UserService userService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
			UserService userService) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

		// Autenticar o usuário
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Gerar token JWT
		String jwt = tokenProvider.generateToken(authentication);

		return ResponseEntity
				.ok(new JwtResponse(jwt, authentication.getName(), userService.getUserRoles(authentication.getName())));
	}
}
