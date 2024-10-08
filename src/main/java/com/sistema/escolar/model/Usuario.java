package com.sistema.escolar.model;

import java.util.List;
import java.util.UUID;
import com.sistema.escolar.dto.UsuarioRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	private String nome;
	private String telefone;
	private String email;
	private String funcao; // Para diferenciar os tipos de usuário (ADM, Professor, Pai)

	@OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
	private List<Aluno> alunos;

	// Construtor utilizando o DTO
	public Usuario(UsuarioRequestDTO data) {
		this.nome = data.nome();
		this.telefone = data.telefone();
		this.email = data.email();
		this.funcao = data.funcao();
	}

	public boolean isPai() {
		return "PAI".equalsIgnoreCase(this.funcao);
	}
}
