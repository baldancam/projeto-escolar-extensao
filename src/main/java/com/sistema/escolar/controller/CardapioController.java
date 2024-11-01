package com.sistema.escolar.controller;

import com.sistema.escolar.model.Aluno;
import com.sistema.escolar.model.Cardapio;
import com.sistema.escolar.model.DiaSemana;
import com.sistema.escolar.repository.CardapioRepository;
import com.sistema.escolar.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cardapio")
public class CardapioController {

	@Autowired
	private CardapioService cardapioService;

	@Autowired
	private CardapioRepository cardapioRepository;

	@GetMapping
	public List<Cardapio> listarCardapio() {
		return cardapioService.listarCardapio();
	}

	@PutMapping("/{diaSemana}")
	@PreAuthorize("hasRole('ADM')")
	public ResponseEntity<Cardapio> atualizarCardapio(@PathVariable DiaSemana diaSemana, @RequestBody String conteudo) {
		Cardapio cardapioAtualizado = cardapioService.atualizarCardapio(diaSemana, conteudo);
		return ResponseEntity.ok(cardapioAtualizado);
	}

	// Endpoint para deletar um conteudo
	@DeleteMapping("{diaSemana}/conteudo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> limparConteudo(@PathVariable DiaSemana diaSemana) {
		cardapioService.limparConteudoDoDia(diaSemana);
		return ResponseEntity.noContent().build(); // Retorna código 204 (No Content)
	}

}
