package com.sistema.escolar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.model.UserRole;
import com.sistema.escolar.repository.UsuarioRepository;
import com.sistema.escolar.repository.NoticiaRepository;

@RestController
@RequestMapping("noticias")
public class NoticiaController {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Método privado para validar se o usuário é ADM
	private Usuario validarUsuarioAdm(UUID usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		if (usuario.getRole() != UserRole.ADMIN) {
			throw new RuntimeException("Usuário não autorizado!");
		}

		return usuario;
	}

	// Endpoint para criar uma nova notícia
	@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
	@PostMapping
	public ResponseEntity<Void> saveNoticia(@RequestBody NoticiasRequestDTO data) {
		// Verifica se o usuário é ADM
		Usuario usuario = validarUsuarioAdm(data.usuarioId());

		// Cria e salva a notícia
		Noticia novaNoticia = new Noticia(data, usuario);
		noticiaRepository.save(novaNoticia);

		return ResponseEntity.ok().build();
	}

	// Endpoint para listar todas as notícias
	@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
	@GetMapping
	public List<NoticiasResponseDTO> getAll() {
		List<NoticiasResponseDTO> noticiaList = noticiaRepository.findAll().stream().map(NoticiasResponseDTO::new)
				.toList();
		return noticiaList; // Retorna a lista de notícias
	}

	// Endpoint para editar uma notícia
	@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateNoticia(@PathVariable UUID id, @RequestBody NoticiasRequestDTO data) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada!"));

		// Verifica se o usuário é ADM
		Usuario usuario = validarUsuarioAdm(data.usuarioId());

		// Atualiza apenas os campos não nulos da notícia
		if (data.titulo() != null) {
			noticia.setTitulo(data.titulo());
		}
		if (data.conteudo() != null) {
			noticia.setConteudo(data.conteudo());
		}
		if (data.imagemUrl() != null) {
			noticia.setImagemUrl(data.imagemUrl());
		}
		if (data.dataPublicacao() != null) {
			noticia.setDataPublicacao(data.dataPublicacao());
		}

		noticiaRepository.save(noticia); // Salva as alterações no banco de dados
		return ResponseEntity.ok().build(); // Retorna uma resposta de sucesso (HTTP 200 OK)
	}

	// Endpoint para deletar uma notícia
	@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNoticia(@PathVariable UUID id) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada!"));

		noticiaRepository.delete(noticia); // Exclui a notícia do banco de dados
		return ResponseEntity.noContent().build(); // Retorna uma resposta sem conteúdo (HTTP 204 No Content)
	}
}
