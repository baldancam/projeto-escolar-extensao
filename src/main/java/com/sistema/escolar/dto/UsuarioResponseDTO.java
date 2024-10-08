package com.sistema.escolar.dto;

import java.util.UUID;

import com.sistema.escolar.model.Usuario;

public record UsuarioResponseDTO(UUID id, String nome, String telefone, String email) {

	public UsuarioResponseDTO(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getTelefone(), usuario.getEmail());
	}
}
