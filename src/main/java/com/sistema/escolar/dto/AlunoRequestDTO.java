package com.sistema.escolar.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoRequestDTO(String nome, LocalDate dataNascimento, String matricula, UUID turmaId, UUID paiId) {
}
