package com.sistema.escolar.dto;

import java.util.UUID;

public record TurmaRequestDTO(String nome, String descricao, UUID professorId) {
}
