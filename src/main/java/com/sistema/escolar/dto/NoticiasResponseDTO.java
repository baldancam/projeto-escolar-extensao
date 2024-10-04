package com.sistema.escolar.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import com.sistema.escolar.model.Noticia;

public record NoticiasResponseDTO(UUID id, String titulo, String conteudo, LocalDateTime dataPublicacao,
		String imagemUrl) {

	// Construtor que aceita a entendida Noticia
	public NoticiasResponseDTO(Noticia noticia) {
		this(noticia.getId(), noticia.getTitulo(), noticia.getConteudo(), noticia.getDataPublicacao(),
				noticia.getImagemUrl());
	}
}
