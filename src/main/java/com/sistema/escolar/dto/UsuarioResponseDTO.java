package com.sistema.escolar.dto;

import java.util.UUID;

import com.sistema.escolar.model.UserRole;
import com.sistema.escolar.model.Usuario;

public record UsuarioResponseDTO(UUID id, String nome, String telefone, String email, String password, UserRole role) {

	public UsuarioResponseDTO(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail(), usuario.getPassword(),
				usuario.getRole());
	}
}
