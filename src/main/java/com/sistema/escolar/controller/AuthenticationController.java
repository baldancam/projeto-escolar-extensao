package com.sistema.escolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.dto.AuthenticationDTO;
import com.sistema.escolar.dto.RegisterDTO;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository repository;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

		var auth = this.authenticationManager.authenticate(usernamePassword);

		return ResponseEntity.ok().build();

	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if (this.repository.findByEmail(data.email()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

		Usuario newUser = new Usuario(data.email(), encryptedPassword, data.role());

		this.repository.save(newUser);

		return ResponseEntity.ok().build();
	}

}
