package com.sistema.escolar.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.sistema.escolar.model.Aluno;

public record AlunoResponseDTO(UUID id, String nome, LocalDate dataNascimento, String matricula, String turma,
		UUID paiId) {

	// Construtor que aceita a entidade Aluno
	public AlunoResponseDTO(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), aluno.getDataNascimento(), aluno.getMatricula(), aluno.getTurma(),
				aluno.getPai() != null ? aluno.getPai().getId() : null);
	}
}
