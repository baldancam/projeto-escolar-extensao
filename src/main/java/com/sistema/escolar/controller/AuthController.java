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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
			UserService userService) {
		logger.debug("AuthController: Constructor initialized");
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return ResponseEntity.ok("Autenticado com sucesso");
	}

}
