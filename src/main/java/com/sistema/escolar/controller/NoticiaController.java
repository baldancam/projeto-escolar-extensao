package com.sistema.escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.dto.NoticiasRequestDTO;
import com.sistema.escolar.dto.NoticiasResponseDTO;
import com.sistema.escolar.model.Funcionario;
import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.repository.FuncionarioRepository;
import com.sistema.escolar.repository.NoticiaRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("noticias")
public class NoticiaController {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository; // Injetar o repositório do Funcionario

	// Criar nova notícia
	@PostMapping
	public ResponseEntity<String> saveNoticia(@RequestBody NoticiasRequestDTO data) {
		Funcionario funcionario = funcionarioRepository.findById(data.funcionarioId())
				.orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

		Noticia noticiaData = new Noticia(data, funcionario);
		noticiaRepository.save(noticiaData);
		return ResponseEntity.ok("Notícia criada com sucesso!");
	}

	// Obter todas as notícias
	@GetMapping
	public ResponseEntity<List<NoticiasResponseDTO>> getAllNoticias() {
		List<NoticiasResponseDTO> noticiaList = noticiaRepository.findAll().stream().map(NoticiasResponseDTO::new)
				.toList();
		return ResponseEntity.ok(noticiaList);
	}
}
