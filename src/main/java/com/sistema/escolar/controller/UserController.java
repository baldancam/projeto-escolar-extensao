package com.sistema.escolar.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.model.Role;
import com.sistema.escolar.request.UserRegistrationRequest;
import com.sistema.escolar.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
		try {
			Set<Role> roles = new HashSet<>();
			for (String roleName : request.getRoles()) {
				Role role = new Role();
				role.setAuthority(roleName); // Define o papel/autoridade
				roles.add(role);
			}

			userService.createUser(request.getUsername(), request.getPassword(), roles);
			return ResponseEntity.ok("Usuário registrado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro ao registrar o usuário: " + e.getMessage());
		}
	}
}
