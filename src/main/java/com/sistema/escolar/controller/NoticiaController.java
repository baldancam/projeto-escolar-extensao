package com.sistema.escolar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.dto.NoticiasRequestDTO;
import com.sistema.escolar.dto.NoticiasResponseDTO;
import com.sistema.escolar.model.Funcionario;
import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.repository.FuncionarioRepository;
import com.sistema.escolar.repository.NoticiaRepository;

@RestController
@RequestMapping("noticias")
public class NoticiaController {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// Endpoint para criar uma nova notícia
	@PostMapping
	public ResponseEntity<Void> saveNoticia(@RequestBody NoticiasRequestDTO data) {
		Funcionario funcionario = funcionarioRepository.findById(data.funcionarioId())
				.orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

		Noticia novaNoticia = new Noticia(data, funcionario); // Cria uma nova notícia a partir do DTO
		noticiaRepository.save(novaNoticia); // Salva a notícia no banco de dados
		return ResponseEntity.ok().build(); // Retorna uma resposta de sucesso (HTTP 200 OK)
	}

	// Endpoint para listar todas as notícias
	@GetMapping
	public List<NoticiasResponseDTO> getAll() {
		List<NoticiasResponseDTO> noticiaList = noticiaRepository.findAll().stream().map(NoticiasResponseDTO::new)
				.toList();
		return noticiaList; // Retorna a lista de notícias
	}

	// Editar notícia existente
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateNoticia(@PathVariable UUID id, @RequestBody NoticiasRequestDTO data) {
		// Buscar a notícia no banco de dados pelo ID
		Noticia noticiaExistente = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada!"));

		// Atualizar os dados da notícia
		noticiaExistente.setTitulo(data.titulo());
		noticiaExistente.setConteudo(data.conteudo());
		noticiaExistente.setDataPublicacao(data.dataPublicacao());
		noticiaExistente.setImagemUrl(data.imagemUrl());

		// Associar o novo funcionário, caso o ID do funcionário tenha mudado
		if (data.funcionarioId() != null) {
			Funcionario funcionario = funcionarioRepository.findById(data.funcionarioId())
					.orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
			noticiaExistente.setFuncionario(funcionario);
		}

		// Salvar as alterações no banco de dados
		noticiaRepository.save(noticiaExistente);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNoticia(@PathVariable UUID id) {
		if (noticiaRepository.existsById(id)) {
			noticiaRepository.deleteById(id);
			return ResponseEntity.noContent().build(); // Retorna 204 No Content se deletado com sucesso
		}
		return ResponseEntity.notFound().build(); // Retorna 404 Not Found se a notícia não for encontrada
	}

}
