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
	@GeneratedValue(generator = "UUID")
	private UUID id; // Alterado para UUID

	private String titulo;
	private String conteudo;
	private LocalDateTime dataPublicacao;
	private String imagemUrl; // URL da imagem

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario; // Alterado para a classe Usuario

	// Construtor que usa o DTO
	public Noticia(NoticiasRequestDTO data, Usuario usuario) {
		this.titulo = data.titulo();
		this.conteudo = data.conteudo();
		this.dataPublicacao = (data.dataPublicacao() != null) ? data.dataPublicacao() : LocalDateTime.now();
		this.usuario = usuario; // Associar o usuário
		this.imagemUrl = data.imagemUrl();
	}

	public Noticia(String titulo, String conteudo, String imagemUrl, Usuario usuario) {
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.imagemUrl = imagemUrl;
		this.dataPublicacao = LocalDateTime.now(); // Definindo a data de publicação como o momento atual
		this.usuario = usuario;
	}

}
