package com.sistema.escolar.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório!")
	private String nome;

	@NotBlank(message = "A matrícula é obrigatória!")
	private String matricula;

	@NotNull(message = "A data de nascimento é obrigatória!")
	private LocalDate dataNascimento;

	@NotBlank(message = "O período é obrigatório!")
	private String periodo;

	// Muitos alunos podem ter um pai
	@ManyToOne
	@JoinColumn(name = "pai_id", referencedColumnName = "id")
	private Pai pai;

	// Construtor padrão
	public Aluno() {
	}

	// Construtor completo
	public Aluno(String nome, String matricula, LocalDate dataNascimento, String periodo, Pai pai) {
		this.nome = nome;
		this.matricula = matricula;
		this.dataNascimento = dataNascimento;
		this.periodo = periodo;
		this.pai = pai;
	}

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Pai getPai() {
		return pai;
	}

	public void setPai(Pai pai) {
		this.pai = pai;
	}

	@Override
	public String toString() {
		return "Aluno{" + "nome='" + nome + '\'' + ", matricula='" + matricula + '\'' + ", dataNascimento="
				+ dataNascimento + ", periodo='" + periodo + '\'' + ", pai=" + pai.getNome() + '}';
	}
}
