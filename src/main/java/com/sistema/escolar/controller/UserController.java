package com.sistema.escolar.controller;

import com.sistema.escolar.model.User;
import com.sistema.escolar.request.UserRegistrationRequest;
import com.sistema.escolar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Endpoint para registrar novos usuários
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
		try {
			userService.createUser(request.getUsername(), request.getPassword(), request.getRole());
			return ResponseEntity.ok("Usuário registrado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro ao registrar o usuário: " + e.getMessage());
		}
	}
}
