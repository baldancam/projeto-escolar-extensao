package com.sistema.escolar.dto;

import java.time.LocalDate;

public record AlunoRequestDTO(String nome, LocalDate dataNascimento) {
}