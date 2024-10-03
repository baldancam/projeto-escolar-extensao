package com.sistema.escolar.dto;

import java.time.LocalDate;

import com.sistema.escolar.model.Funcionario;
import com.sistema.escolar.model.Noticia;

public record NoticiasResponseDTO(Long id, String conteudo, LocalDate dataPublicacao, String imagemUrl, String titulo) {

	// Construtor que aceita uma entidade Noticia
	public NoticiasResponseDTO(Noticia noticia) {
		this(noticia.getId(), noticia.getConteudo(), noticia.getDataPublicacao(), noticia.getImagemUrl(),
				noticia.getTitulo());
	}
}
