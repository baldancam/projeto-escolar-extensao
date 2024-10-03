package com.sistema.escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.dto.FuncionarioRequestDTO;
import com.sistema.escolar.dto.FuncionarioResponseDTO;
import com.sistema.escolar.model.Funcionario;
import com.sistema.escolar.repository.FuncionarioRepository;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// Endpoint para criar um novo funcionário
	@PostMapping
	public ResponseEntity<Void> saveFuncionario(@RequestBody FuncionarioRequestDTO data) {
		Funcionario novoFuncionario = new Funcionario(data); // Cria um novo funcionário a partir do DTO
		funcionarioRepository.save(novoFuncionario); // Salva o funcionário no banco de dados
		return ResponseEntity.ok().build(); // Retorna uma resposta de sucesso (HTTP 200 OK)
	}

	// Endpoint para listar todos os funcionários
	@GetMapping
	public List<FuncionarioResponseDTO> getAll() {
		List<FuncionarioResponseDTO> funcionarioList = funcionarioRepository.findAll().stream()
				.map(FuncionarioResponseDTO::new).toList();
		return funcionarioList; // Retorna a lista de funcionários
	}
}
