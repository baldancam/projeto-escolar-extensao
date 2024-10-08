package com.sistema.escolar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sistema.escolar.dto.NoticiasRequestDTO;
import com.sistema.escolar.dto.NoticiasResponseDTO;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.repository.UsuarioRepository;
import com.sistema.escolar.repository.NoticiaRepository;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Endpoint para criar uma nova notícia
	@PostMapping
	public ResponseEntity<Void> saveNoticia(@RequestBody NoticiasRequestDTO data) {
		// Verifica se o usuário existe
		Usuario usuario = usuarioRepository.findById(data.usuarioId())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		// Verifica se o usuário tem função de "ADM"
		if (!"ADM".equalsIgnoreCase(usuario.getFuncao())) {
			return ResponseEntity.status(403).build(); // Retorna 403 Forbidden se não for ADM
		}

		// Cria e salva a notícia
		Noticia novaNoticia = new Noticia(data, usuario);
		noticiaRepository.save(novaNoticia);

		return ResponseEntity.ok().build();
	}

	// Endpoint para listar todas as notícias
	@GetMapping
	public List<NoticiasResponseDTO> getAll() {
		List<NoticiasResponseDTO> noticiaList = noticiaRepository.findAll().stream().map(NoticiasResponseDTO::new)
				.toList();
		return noticiaList; // Retorna a lista de notícias
	}
}
