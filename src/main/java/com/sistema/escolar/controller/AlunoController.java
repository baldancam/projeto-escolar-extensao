package com.sistema.escolar.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.escolar.dto.AlunoRequestDTO;
import com.sistema.escolar.dto.AlunoResponseDTO;
import com.sistema.escolar.model.Aluno;
import com.sistema.escolar.model.Turma;
import com.sistema.escolar.model.UserRole;
import com.sistema.escolar.model.Usuario;
import com.sistema.escolar.repository.AlunoRepository;
import com.sistema.escolar.repository.TurmaRepository;
import com.sistema.escolar.repository.UsuarioRepository;

@RestController
@RequestMapping("alunos")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	// Método privado para validar se o usuário é PAI
	private Usuario validarUsuarioPai(UUID paiId) {
		Usuario pai = usuarioRepository.findById(paiId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		// Verifica se o usuário é do tipo PAI
		if (pai.getRole() != UserRole.PAI) {
			throw new RuntimeException("O usuário informado não é um PAI.");
		}

		return pai;
	}

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
		Usuario pai = validarUsuarioPai(idPai);

		List<AlunoResponseDTO> alunoList = alunoRepository.findByPai(pai).stream().map(AlunoResponseDTO::new).toList();
		return alunoList;
	}

	
	// Endpoint para criar um novo aluno (verifica apenas se o paiId é válido)
	@PostMapping
	public ResponseEntity<Void> saveAluno(@RequestBody AlunoRequestDTO data) {
		// Verifica se o usuário associado ao aluno é PAI
		Usuario pai = validarUsuarioPai(data.paiId());

		// Busca a turma pelo ID passado no DTO
		Turma turma = turmaRepository.findById(data.turmaId())
				.orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

		// Cria e salva o aluno
		Aluno novoAluno = new Aluno(data.nome(), data.dataNascimento(), data.matricula(), turma, pai);
		alunoRepository.save(novoAluno);

		return ResponseEntity.ok().build();
	}

	// Endpoint para editar um aluno
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateAluno(@PathVariable UUID id, @RequestBody AlunoRequestDTO data) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

		// Se necessário, busca e atualiza a turma
		if (data.turmaId() != null) {
			Turma turma = turmaRepository.findById(data.turmaId())
					.orElseThrow(() -> new RuntimeException("Turma não encontrada!"));
			aluno.setTurma(turma);
		}

		if (data.nome() != null)
			aluno.setNome(data.nome());
		if (data.dataNascimento() != null)
			aluno.setDataNascimento(data.dataNascimento());
		if (data.matricula() != null)
			aluno.setMatricula(data.matricula());

		alunoRepository.save(aluno);
		return ResponseEntity.ok().build();
	}

	// Endpoint para deletar um aluno
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAluno(@PathVariable UUID id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado!"));

		alunoRepository.delete(aluno); // Exclui o aluno do banco de dados
		return ResponseEntity.noContent().build(); // Retorna uma resposta sem conteúdo (HTTP 204 No Content)
	}
}
