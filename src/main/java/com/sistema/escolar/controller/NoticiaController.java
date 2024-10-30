package com.sistema.escolar.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.escolar.dto.NoticiasRequestDTO;
import com.sistema.escolar.dto.NoticiasResponseDTO;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.model.UserRole;
import com.sistema.escolar.repository.UsuarioRepository;
import com.sistema.escolar.repository.NoticiaRepository;
import com.sistema.escolar.service.S3Service;

@RestController
@RequestMapping("noticias")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class NoticiaController {

	@Autowired
	private NoticiaRepository noticiaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private S3Service s3Service; // Injeta o serviço S3 para upload de imagens

	// Método privado para validar se o usuário é ADM
	private Usuario validarUsuarioAdm(UUID usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		if (usuario.getRole() != UserRole.ADMIN) {
			throw new RuntimeException("Usuário não autorizado!");
		}

		return usuario;
	}

	// Endpoint para criar uma nova notícia com imagem
	@PostMapping
	public ResponseEntity<Void> saveNoticiaComImagem(@RequestParam("file") MultipartFile file,
			@RequestParam("titulo") String titulo, @RequestParam("conteudo") String conteudo,
			@RequestParam("usuarioId") UUID usuarioId) {
		// Verifica se o usuário é ADM
		Usuario usuario = validarUsuarioAdm(usuarioId);

		try {
			// Salva o arquivo localmente (opcional)
			String localPath = "/tmp/" + file.getOriginalFilename();
			file.transferTo(new File(localPath));

			// Gera um nome de arquivo único
			String fileName = s3Service.generateFileName(file.getOriginalFilename());

			// Faz o upload da imagem para o S3 com o nome gerado
			String imagemUrl = s3Service.uploadFile(localPath, fileName);

			// Cria e salva a notícia com a URL da imagem
			Noticia novaNoticia = new Noticia(titulo, conteudo, imagemUrl, usuario);
			noticiaRepository.save(novaNoticia);

			return ResponseEntity.ok().build();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build(); // Retorna um erro interno do servidor
		}

	}

	// Endpoint para listar todas as notícias
	@GetMapping
	public List<NoticiasResponseDTO> getAll() {
		List<NoticiasResponseDTO> noticiaList = noticiaRepository.findAll().stream().map(NoticiasResponseDTO::new)
				.toList();
		return noticiaList;
	}

	// Endpoint para editar uma notícia
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateNoticia(@PathVariable UUID id, @RequestBody NoticiasRequestDTO data) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada!"));

		// Verifica se o usuário é ADM
		Usuario usuario = validarUsuarioAdm(data.usuarioId());

		// Atualiza os campos da notícia
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

		noticiaRepository.save(noticia);
		return ResponseEntity.ok().build();
	}

	// Método para deletar uma notícia e a imagem correspondente
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNoticia(@PathVariable UUID id) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada!"));

		// Deleta a imagem do S3
		if (noticia.getImagemUrl() != null) {
			String fileName = noticia.getImagemUrl().substring(noticia.getImagemUrl().lastIndexOf("/") + 1);
			s3Service.deleteFile(fileName);
		}

		// Deleta a notícia do banco de dados
		noticiaRepository.delete(noticia);

		return ResponseEntity.noContent().build();
	}
}
