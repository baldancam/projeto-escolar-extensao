package com.sistema.escolar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;
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
	public ResponseEntity<Cardapio> atualizarCardapio(
	        @PathVariable DiaSemana diaSemana,
	        @RequestBody Map<String, Object> conteudo) { // Aceita JSON como um Map
	    // Converte o conteúdo para string apenas se necessário
	    String conteudoJson;
	    try {
	        conteudoJson = new ObjectMapper().writeValueAsString(conteudo);
	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao converter JSON", e);
	    }

	    Cardapio cardapioAtualizado = cardapioService.atualizarCardapio(diaSemana, conteudoJson);
	    return ResponseEntity.ok(cardapioAtualizado);
	}


	// Endpoint para deletar um conteudo
	@DeleteMapping("{diaSemana}/conteudo")
	public ResponseEntity<Void> limparConteudo(@PathVariable DiaSemana diaSemana) {
		cardapioService.limparConteudoDoDia(diaSemana);
		return ResponseEntity.noContent().build(); // Retorna código 204 (No Content)
	}

}
