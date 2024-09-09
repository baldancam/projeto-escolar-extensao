package com.sistema.escolar.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Aluno {

	@NotBlank(message = "O nome é obrigatório!")
	@Size(min = 3, max = 100, message = "O nome não pode ter menos de 3 caracteres e mais de 100!")
	private String nome;

	@NotBlank(message = "A matrícula é obrigatória!")
	private String matricula;
	
	@NotNull(message = "A data de nascimento é obrigatória!")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O período é obrigatório!")
	private String periodo;

	// Construtor
	public Aluno(String nome, String matricula, LocalDate dataNascimento, String periodo) {
		this.nome = nome;
		this.matricula = matricula;
		this.dataNascimento = dataNascimento;
		this.periodo = periodo;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "Aluno{" + "nome='" + nome + '\'' + ", matricula='" + matricula + '\'' + ", dataNascimento="
				+ dataNascimento + ", periodo='" + periodo + '\'' + '}';
	}
}
