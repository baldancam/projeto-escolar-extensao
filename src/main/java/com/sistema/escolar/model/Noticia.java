package com.sistema.escolar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sistema.escolar.dto.NoticiasRequestDTO;

@Entity(name = "noticias")
@Table(name = "tb_noticias")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; // Alterado para UUID

	private String titulo;
	private String conteudo;
	private LocalDateTime dataPublicacao;
	private String imagemUrl; // URL da imagem

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	// Construtor que usa o DTO
	public Noticia(NoticiasRequestDTO data, Funcionario funcionario) {
		this.titulo = data.titulo();
		this.conteudo = data.conteudo();
		this.dataPublicacao = data.dataPublicacao();
		this.funcionario = funcionario; // Associar o funcionário
		this.imagemUrl = data.imagemUrl();
	}
}
