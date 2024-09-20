package com.sistema.escolar.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class Professor extends Funcionario {

	@NotBlank(message = "A turma é obrigatória!")
	private String turma;

	// Construtor completo
	public Professor(String nome, String matricula, LocalDate dataNascimento, String telefone, String email,
			String turma) {
		super(nome, matricula, dataNascimento, telefone, email, "PROFESSOR");
		this.turma = turma;
	}

	// Getters e Setters
	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Professor{" + "nome='" + getNome() + '\'' + ", matricula='" + getMatricula() + '\''
				+ ", dataNascimento=" + getDataNascimento() + ", telefone='" + getTelefone() + '\'' + ", email='"
				+ getEmail() + '\'' + ", turma='" + turma + '\'' + '}';
	}
}
