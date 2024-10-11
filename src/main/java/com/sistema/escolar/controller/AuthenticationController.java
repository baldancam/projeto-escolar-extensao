package com.sistema.escolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.dto.AuthenticationDTO;
import com.sistema.escolar.dto.LoginResponseDTO;
import com.sistema.escolar.dto.RegisterDTO;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.repository.UsuarioRepository;
import com.sistema.escolar.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((Usuario) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));

	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
		// Verifica se o email já está em uso
		if (this.repository.findByEmail(data.email()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já registrado!");
		}

		// Se o email não estiver em uso, procede com o registro
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

		Usuario newUser = new Usuario(data.email(), encryptedPassword, data.role());

		this.repository.save(newUser);

		return ResponseEntity.ok("Usuário registrado com sucesso!");
	}

}
