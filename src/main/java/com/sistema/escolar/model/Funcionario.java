package com.sistema.escolar.model;

import java.time.LocalDate;
import java.util.UUID;
import com.sistema.escolar.dto.FuncionarioRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "tb_funcionario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Funcionario {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	private String nome;
	private String matricula;
	private LocalDate dataNascimento;
	private String telefone;
	private String email;
	private String funcao;

	// Construtor utilizando o DTO
	public Funcionario(FuncionarioRequestDTO data) {
		this.nome = data.nome();
		this.matricula = data.matricula();
		this.dataNascimento = data.dataNascimento();
		this.telefone = data.telefone();
		this.email = data.email();
		this.funcao = data.funcao();
	}
}
