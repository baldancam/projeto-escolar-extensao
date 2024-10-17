package com.sistema.escolar.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.sistema.escolar.model.Aluno;

public record AlunoResponseDTO(UUID id, String nome, LocalDate dataNascimento, String matricula, UUID turmaId,
		UUID paiId) {

	// Construtor que aceita a entidade Aluno
	public AlunoResponseDTO(Aluno aluno) {
		// Corrigido para pegar o UUID da turma e do pai
		this(aluno.getId(), aluno.getNome(), aluno.getDataNascimento(), aluno.getMatricula(),
				aluno.getTurma() != null ? aluno.getTurma().getId() : null, // Obter o ID da Turma
				aluno.getPai() != null ? aluno.getPai().getId() : null); // Obter o ID do Pai
	}
}
