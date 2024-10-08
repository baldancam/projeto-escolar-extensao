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

import com.sistema.escolar.dto.UsuarioRequestDTO;
import com.sistema.escolar.dto.UsuarioResponseDTO;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Endpoint para criar um novo usuario
	@PostMapping
	public ResponseEntity<Void> saveUsuario(@Valid @RequestBody UsuarioRequestDTO data) {
		Usuario novoUsuario = new Usuario(data); // Cria um novo usuario a partir do DTO
		usuarioRepository.save(novoUsuario); // Salva o usuario no banco de dados
		return ResponseEntity.ok().build(); // Retorna uma resposta de sucesso (HTTP 200 OK)
	}

	// Endpoint para listar todos os usuarios
	@GetMapping
	public List<UsuarioResponseDTO> getAll() {
		List<UsuarioResponseDTO> usuarioList = usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new)
				.toList();
		return usuarioList; // Retorna a lista de usuarios
	}

	// Buscar um usuário por ID (GET)
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> getUsuarioById(@PathVariable UUID id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
	}

	// Editar um usuário (PUT)
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUsuario(@PathVariable UUID id, @RequestBody UsuarioRequestDTO data) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		// Atualiza apenas os campos não nulos
		if (data.nome() != null) {
			usuario.setNome(data.nome());
		}
		if (data.telefone() != null) {
			usuario.setTelefone(data.telefone());
		}
		if (data.email() != null) {
			usuario.setEmail(data.email());
		}

		usuarioRepository.save(usuario); // Salva o usuário atualizado no banco de dados
		return ResponseEntity.ok().build(); // Retorna uma resposta de sucesso (HTTP 200 OK)
	}

	// Deletar um usuário (DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
		Usuario usuarioExistente = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		usuarioRepository.delete(usuarioExistente); // Exclui o usuário do banco de dados
		return ResponseEntity.noContent().build();
	}

}
