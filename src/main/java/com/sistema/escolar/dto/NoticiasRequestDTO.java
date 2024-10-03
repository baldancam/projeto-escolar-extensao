package com.sistema.escolar.dto;

import java.time.LocalDate;

public record NoticiasRequestDTO(String titulo, String conteudo, LocalDate dataPublicacao, Long funcionarioId,
		String imagemUrl) {
}
