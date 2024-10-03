package com.sistema.escolar.dto;

import java.time.LocalDate;

import com.sistema.escolar.model.Funcionario;

public record NoticiasRequestDTO(String titulo, String conteudo, LocalDate dataPublicacao, Funcionario funcionario,
		String imagemUrl) {
}
