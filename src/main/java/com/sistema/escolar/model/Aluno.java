package com.sistema.escolar.model;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

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
	private String turma;

	@ManyToOne
	@JoinColumn(name = "pai_id")
	private Usuario pai; // Relaciona o aluno a um pai

	public Aluno(String nome, LocalDate dataNascimento, String matricula, String turma, Usuario pai) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.matricula = matricula;
		this.turma = turma;
		this.pai = pai;
	}
}
