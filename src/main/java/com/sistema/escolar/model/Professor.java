package com.sistema.escolar.model;

import com.sistema.escolar.dto.FuncionarioRequestDTO;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Professor extends Funcionario {

	// Campos específicos de Professor
	private String turma;

	// Construtor que chama o super (Funcionario)
	public Professor(FuncionarioRequestDTO data, String turma) {
		super(data); // Chama o construtor da superclasse
		this.turma = turma;
	}

	// Getter e setter
	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;

	}
}
