package com.sistema.escolar.model;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
@NoArgsConstructor
public class Aluno {

	@Id
	@GeneratedValue(generator = "UUID")
	@UuidGenerator
	private UUID id;

	private String nome;
	private LocalDate dataNascimento;
	private String matricula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "turma_id")
	@JsonIgnore
	private Turma turma;
	// Relaciona o aluno a uma turma

	@ManyToOne
	@JoinColumn(name = "pai_id")
	@JsonIgnore
	private Usuario pai; // Relaciona o aluno a um pai

	public Aluno(String nome, LocalDate dataNascimento, String matricula, Turma turma, Usuario pai) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.turma = turma;
		this.pai = pai;
	}
}
