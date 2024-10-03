package com.sistema.escolar.dto;

import java.time.LocalDate;
import com.sistema.escolar.model.Noticia;

public record NoticiasResponseDTO(Long id, String titulo, String conteudo, LocalDate dataPublicacao, String imagemUrl) {

	// Construtor que aceita uma entidade Noticia
	public NoticiasResponseDTO(Noticia noticia) {
		this(noticia.getId(), noticia.getTitulo(), noticia.getConteudo(), noticia.getDataPublicacao(),
				noticia.getImagemUrl());
	}
}
