package com.sistema.escolar.dto;

import java.time.LocalDate;

import com.sistema.escolar.model.Funcionario;

public record FuncionarioResponseDTO(Long id, String nome, String matricula, LocalDate dataNascimento, String telefone,
		String email, String funcao) {

	public FuncionarioResponseDTO(Funcionario funcionario) {
		this(funcionario.getId(), funcionario.getNome(), funcionario.getMatricula(), funcionario.getDataNascimento(),
				funcionario.getTelefone(), funcionario.getEmail(), funcionario.getFuncao());
	}
}
