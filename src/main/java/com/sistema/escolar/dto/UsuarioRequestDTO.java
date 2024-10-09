package com.sistema.escolar.dto;

import java.util.List;

import com.sistema.escolar.model.UserRole;

public record UsuarioRequestDTO(String nome, String telefone, String email, String password, String funcao,
		UserRole role, List<AlunoRequestDTO> alunos) {
}