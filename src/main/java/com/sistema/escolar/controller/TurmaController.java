package com.sistema.escolar.controller;

import com.sistema.escolar.dto.AlunoResponseDTO;
import com.sistema.escolar.dto.TurmaRequestDTO;
import com.sistema.escolar.model.Turma;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.repository.TurmaRepository;
import com.sistema.escolar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/turmas")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Endpoint para criar uma nova turma
	@PostMapping
	public ResponseEntity<Void> createTurma(@RequestBody TurmaRequestDTO data) {
		// Valida se o professor existe
		Usuario professor = usuarioRepository.findById(data.professorId())
				.orElseThrow(() -> new RuntimeException("Professor não encontrado!"));

		// Cria a nova turma
		Turma novaTurma = new Turma(data.nome(), data.descricao(), professor);
		turmaRepository.save(novaTurma);

		return ResponseEntity.ok().build();
	}

	// Endpoint para listar todas as turmas
	@GetMapping
	public ResponseEntity<List<Turma>> getAllTurmas() {
		return ResponseEntity.ok(turmaRepository.findAll());
	}

	// Endpoint para editar uma turma
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateTurma(@PathVariable UUID id, @RequestBody TurmaRequestDTO data) {
		// Busca a turma pelo ID
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

		// Atualiza os dados da turma
		if (data.nome() != null)
			turma.setNome(data.nome());
		if (data.descricao() != null)
			turma.setDescricao(data.descricao());

		// Atualiza o professor, se fornecido
		if (data.professorId() != null) {
			Usuario professor = usuarioRepository.findById(data.professorId())
					.orElseThrow(() -> new RuntimeException("Professor não encontrado!"));
			turma.setProfessor(professor);
		}

		turmaRepository.save(turma);
		return ResponseEntity.ok().build();
	}

	// Endpoint para listar alunos de uma turma vinculados ao professor
	@GetMapping("/turma/{turmaId}/professor/{professorId}")
	public ResponseEntity<List<AlunoResponseDTO>> getAlunosDaTurmaDoProfessor(@PathVariable UUID turmaId,
			@PathVariable UUID professorId) {
		// Busca a turma e verifica se pertence ao professor
		Turma turma = turmaRepository.findById(turmaId)
				.orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

		if (!turma.getProfessor().getId().equals(professorId)) {
			return ResponseEntity.status(403).build();
		}

		List<AlunoResponseDTO> alunos = turma.getAlunos().stream().map(AlunoResponseDTO::new).toList();

		return ResponseEntity.ok(alunos);
	}

	// Endpoint para deletar uma turma
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTurma(@PathVariable UUID id) {
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

		turmaRepository.delete(turma);
		return ResponseEntity.noContent().build(); // HTTP 204 No Content
	}
}
