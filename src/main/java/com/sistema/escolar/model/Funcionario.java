package com.sistema.escolar.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String matricula;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	private String funcao;

	// Construtor completo
	public Funcionario(String nome, String matricula, LocalDate dataNascimento, String telefone, String email,
			String funcao) {
		this.nome = nome;
		this.matricula = matricula;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.funcao = funcao;
	}

	// Construtor padrão (necessário para o JPA)
	public Funcionario() {
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario{" + "nome='" + nome + '\'' + ", matricula='" + matricula + '\'' + ", dataNascimento="
				+ dataNascimento + ", telefone='" + telefone + '\'' + ", email='" + email + '\'' + ", funcao='" + funcao
				+ '\'' + '}';
	}
}
