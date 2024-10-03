package com.sistema.escolar.dto;

import java.time.LocalDate;

public record FuncionarioRequestDTO(String nome, String matricula, LocalDate dataNascimento, String telefone,
		String email, String funcao) {
}
