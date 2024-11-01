package com.sistema.escolar.service;

import com.sistema.escolar.model.Cardapio;
import com.sistema.escolar.model.DiaSemana;
import com.sistema.escolar.repository.CardapioRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {

	@Autowired
	private CardapioRepository cardapioRepository;

	public List<Cardapio> listarCardapio() {
		return cardapioRepository.findAll();
	}

	@PostConstruct
	public void initCardapioDias() {
		// Verifica se a tabela está vazia antes de inserir os dias
		for (DiaSemana dia : DiaSemana.values()) {
			if (!cardapioRepository.existsByDiaSemana(dia)) {
				Cardapio cardapio = new Cardapio(dia, "");
				cardapioRepository.save(cardapio);
			}
		}
	}

	@Transactional
	public Cardapio atualizarCardapio(DiaSemana diaSemana, String conteudo) {
		Cardapio cardapio = cardapioRepository.findByDiaSemana(diaSemana)
				.orElseThrow(() -> new RuntimeException("Dia da semana não encontrado."));
		cardapio.setConteudo(conteudo);
		return cardapioRepository.save(cardapio);
	}

	public void limparConteudoDoDia(DiaSemana diaSemana) {
		Optional<Cardapio> cardapioOpt = cardapioRepository.findByDiaSemana(diaSemana);
		if (cardapioOpt.isPresent()) {
			Cardapio cardapio = cardapioOpt.get();
			cardapio.setConteudo(""); // Zera o conteúdo
			cardapioRepository.save(cardapio); // Salva a atualização
		}
	}

}
