package com.sistema.escolar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.escolar.dto.AlunoRequestDTO;
import com.sistema.escolar.dto.AlunoResponseDTO;
import com.sistema.escolar.model.Aluno;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.repository.AlunoRepository;
import com.sistema.escolar.repository.UsuarioRepository;

@RestController
@RequestMapping("alunos")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Endpoint para listar todos os alunos (todos os usuários podem ver)
	@GetMapping
	public List<AlunoResponseDTO> getAll() {
		List<AlunoResponseDTO> alunoList = alunoRepository.findAll().stream().map(AlunoResponseDTO::new).toList();
		return alunoList;
	}

	// Endpoint para listar alunos vinculados ao pai (visualização restrita aos
	// pais)
	@GetMapping("/meus-alunos/{idPai}")
	public List<AlunoResponseDTO> getAlunosDoPai(@PathVariable UUID idPai) {
		Usuario pai = usuarioRepository.findById(idPai).orElseThrow(() -> new RuntimeException("Pai não encontrado!"));

		List<AlunoResponseDTO> alunoList = alunoRepository.findByPai(pai).stream().map(AlunoResponseDTO::new).toList();
		return alunoList; // Retorna os alunos vinculados ao pai
	}

	// Endpoint para criar um novo aluno (apenas ADM pode criar)
	@PostMapping
	public ResponseEntity<Void> saveAluno(@RequestBody AlunoRequestDTO data) {
		Usuario usuario = usuarioRepository.findById(data.usuarioId())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		if (!"ADM".equalsIgnoreCase(usuario.getFuncao())) {
			return ResponseEntity.status(403).build();
		}

		Aluno novoAluno = new Aluno(data.nome(), data.dataNascimento(), data.matricula(), data.turma(), usuario);
		alunoRepository.save(novoAluno);

		return ResponseEntity.ok().build();
	}

	// Endpoint para editar um aluno (apenas ADM pode editar)
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateAluno(@PathVariable UUID id, @RequestBody AlunoRequestDTO data) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

		Usuario usuario = usuarioRepository.findById(data.usuarioId())
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		if (!"ADM".equalsIgnoreCase(usuario.getFuncao())) {
			return ResponseEntity.status(403).build();
		}

		if (data.nome() != null)
			aluno.setNome(data.nome());
		if (data.dataNascimento() != null)
			aluno.setDataNascimento(data.dataNascimento());
		if (data.matricula() != null)
			aluno.setMatricula(data.matricula());
		if (data.turma() != null)
			aluno.setTurma(data.turma());

		alunoRepository.save(aluno);
		return ResponseEntity.ok().build();
	}

	// Endpoint para deletar um aluno (apenas ADM pode deletar)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAluno(@PathVariable UUID id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

		alunoRepository.delete(aluno); // Exclui o aluno do banco de dados
		return ResponseEntity.noContent().build(); // Retorna uma resposta sem conteúdo (HTTP 204 No Content)
	}

}