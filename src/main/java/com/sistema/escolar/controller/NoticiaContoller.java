package com.sistema.escolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.escolar.dto.NoticiasRequestDTO;
import com.sistema.escolar.dto.NoticiasResponseDTO;
import com.sistema.escolar.model.Noticia;
import com.sistema.escolar.repository.NoticiaRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("noticias")
public class NoticiaContoller {

	@Autowired
	private NoticiaRepository repository;

	@PostMapping
	public void saveNoticia(@RequestBody NoticiasRequestDTO data) {
		Noticia noticiaData = new Noticia(data);

		repository.save(noticiaData);

		return;

	}

	@GetMapping
	public List<NoticiasResponseDTO> getAll() {
		List<NoticiasResponseDTO> noticiaList = repository.findAll().stream().map(NoticiasResponseDTO::new).toList();
		return noticiaList;
	}

}
