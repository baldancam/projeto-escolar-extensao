package com.sistema.escolar.dto;

import java.util.UUID;
import java.time.LocalDateTime;

public record NoticiasRequestDTO(String titulo, String conteudo, LocalDateTime dataPublicacao, UUID usuarioId,
		String imagemUrl) {
}
