package com.sistema.escolar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_pai")
public class Pai {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome é obrigatório!")
	private String nome;

	@NotBlank(message = "O telefone é obrigatório!")
	private String telefone;

	@Email(message = "E-mail inválido!")
	private String email;

	// Um pai pode ter vários alunos
	@OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
	private List<Aluno> alunos;

	// Construtor padrão
	public Pai() {
	}

	// Construtor com todos os atributos
	public Pai(String nome, String telefone, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
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

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		return "Pai{" + "nome='" + nome + '\'' + ", telefone='" + telefone + '\'' + ", email='" + email + '\''
				+ ", alunos=" + alunos + '}';
	}
}
