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

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		// Autenticar o usuário
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Definir contexto de segurança
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Gerar o token JWT
		String jwt = tokenProvider.generateToken(authentication);

		// Retornar a resposta com o token JWT e informações do usuário
		return ResponseEntity
				.ok(new JwtResponse(jwt, authentication.getName(), userService.getUserRoles(authentication.getName())));
	}
}
