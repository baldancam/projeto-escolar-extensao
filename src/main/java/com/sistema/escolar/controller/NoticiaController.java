package com.sistema.escolar.controller;

import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

	@Autowired
	private NoticiaRepository noticiaRepository;

	// Criar noticia
	@PostMapping
	public ResponseEntity<Noticia> criarNoticia(@RequestBody Noticia noticia) {
		Noticia novaNoticia = noticiaRepository.save(noticia);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaNoticia);
	}

	// Mostrar noticias
	@GetMapping
	public List<Noticia> listarNoticias() {
		return noticiaRepository.findAll();
	}

	// Atualizar uma notícia existente
	@PutMapping("/{id}")
	public ResponseEntity<Noticia> updateNoticia(@PathVariable Long id, @RequestBody Noticia noticiaDetalhes) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada"));
		noticia.setTitulo(noticiaDetalhes.getTitulo());
		noticia.setConteudo(noticiaDetalhes.getConteudo());
		noticia.setDataPublicacao(noticiaDetalhes.getDataPublicacao());

		Noticia noticiaAtualizada = noticiaRepository.save(noticia);
		return ResponseEntity.ok(noticiaAtualizada);
	}

	// Deletar uma notícia
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNoticia(@PathVariable Long id) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Notícia não encontrada"));
		noticiaRepository.delete(noticia);
		return ResponseEntity.noContent().build();
	}
}
