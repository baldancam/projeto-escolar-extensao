package com.sistema.escolar.dto;

import java.util.List;

public record UsuarioRequestDTO(String nome, String telefone, String email, String funcao,
		List<AlunoRequestDTO> alunos) {
}